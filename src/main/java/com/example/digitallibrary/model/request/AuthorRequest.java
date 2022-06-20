package com.example.digitallibrary.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class AuthorRequest {

    private final String firstname;
    private final String lastname;
}
