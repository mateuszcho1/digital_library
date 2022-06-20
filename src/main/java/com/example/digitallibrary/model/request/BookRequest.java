package com.example.digitallibrary.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class BookRequest {

    private final String title;
    private final long authorId;
    private final int publishingYear;
    private final String bookType;
}
