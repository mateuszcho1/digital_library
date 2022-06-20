package com.example.digitallibrary.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class UserRequest {

    private final String username;
    private final String password;
}
