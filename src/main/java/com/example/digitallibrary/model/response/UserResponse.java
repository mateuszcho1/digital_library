package com.example.digitallibrary.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class UserResponse {

    private final long id;
    private final String username;
    private final String password;
    private final List<String> bookShelf;
}
