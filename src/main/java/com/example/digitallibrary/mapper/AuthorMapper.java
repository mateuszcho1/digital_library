package com.example.digitallibrary.mapper;

import com.example.digitallibrary.model.entity.Author;
import com.example.digitallibrary.model.entity.Book;
import com.example.digitallibrary.model.request.AuthorRequest;
import com.example.digitallibrary.model.response.AuthorResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public static Author map(final AuthorRequest authorRequest) {
        return Author.builder()
                .firstname(authorRequest.getFirstname())
                .lastname(authorRequest.getLastname())
                .build();
    }

    public static AuthorResponse map(final Author author) {
        List<String> books = author.getBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        return AuthorResponse.builder()
                .id(author.getId())
                .firstname(author.getFirstname())
                .lastname(author.getLastname())
                .books(books)
                .build();
    }
}
