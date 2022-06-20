package com.example.digitallibrary.service;

import com.example.digitallibrary.mapper.BookMapper;
import com.example.digitallibrary.model.entity.Author;
import com.example.digitallibrary.model.entity.Book;
import com.example.digitallibrary.model.request.BookRequest;
import com.example.digitallibrary.model.response.BookResponse;
import com.example.digitallibrary.repository.AuthorRepository;
import com.example.digitallibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResponse create(final BookRequest bookRequest) {
        Book book = BookMapper.map(bookRequest);
        bookRepository.findByTitle(bookRequest.getTitle()
        ).ifPresent(b -> {
            throw new EntityExistsException();
        });
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(EntityNotFoundException::new);
        book.setAuthor(author);
        return BookMapper.map(bookRepository.save(book));
    }

    public BookResponse getById(final Long bookId) {
        return BookMapper.map(getBookByIdFromDatabase(bookId));
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toList());
    }

    public BookResponse updateById(
            final Long bookId,
            final BookRequest bookRequest
    ) {
        final Book bookFromDatabase = getBookByIdFromDatabase(bookId);
        final Author author = authorRepository.findById(
                bookRequest.getAuthorId()
        ).orElseThrow(EntityNotFoundException::new);

        bookFromDatabase.setTitle(bookRequest.getTitle());
        bookFromDatabase.setAuthor(author);
        bookFromDatabase.setPublishingYear(bookRequest.getPublishingYear());
        bookFromDatabase.setBookType(bookRequest.getBookType());
        return BookMapper.map(bookRepository.save(bookFromDatabase));
    }

    public void removeById(final Long bookId) {
        getBookByIdFromDatabase(bookId);
        bookRepository.deleteById(bookId);
    }

    private Book getBookByIdFromDatabase(final Long bookId) {
        final Optional<Book> bookFromDatabase = bookRepository.findById(bookId);
        return bookFromDatabase.orElseThrow(EntityNotFoundException::new);
    }
}
