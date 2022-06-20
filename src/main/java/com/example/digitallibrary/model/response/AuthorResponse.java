package com.example.digitallibrary.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class AuthorResponse {

    private final long id;
    private final String firstname;
    private final String lastname;
    private final List<String> books;
}
