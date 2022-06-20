package com.example.digitallibrary.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BookResponse {

    private final long id;
    private final String title;
    private final String authorFirstname;
    private final String authorLastname;
    private final int publishingYear;
    private final String bookType;
    private final List<String> users;
    private final Boolean isTaken;
}
