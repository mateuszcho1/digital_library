package com.example.digitallibrary.mapper;

import com.example.digitallibrary.model.entity.Author;
import com.example.digitallibrary.model.entity.Book;
import com.example.digitallibrary.model.entity.UserEntity;
import com.example.digitallibrary.model.request.BookRequest;
import com.example.digitallibrary.model.response.BookResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public static Book map(final BookRequest bookRequest) {
        final Author author = Author.builder()
                .id(bookRequest.getAuthorId())
                .build();
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(author)
                .publishingYear(bookRequest.getPublishingYear())
                .bookType(bookRequest.getBookType())
                .isTaken(false)
                .build();
    }

    public static BookResponse map(final Book book) {
        List<String> users = book.getUsers()
                .stream()
                .map(UserEntity::getUsername)
                .collect(Collectors.toList());
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorFirstname(book.getAuthor().getFirstname())
                .authorLastname(book.getAuthor().getLastname())
                .publishingYear(book.getPublishingYear())
                .bookType(book.getBookType())
                .users(users)
                .isTaken(book.getIsTaken())
                .build();
    }
}
