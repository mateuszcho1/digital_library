package com.example.digitallibrary.controller;

import com.example.digitallibrary.model.request.AuthorRequest;
import com.example.digitallibrary.model.response.AuthorResponse;
import com.example.digitallibrary.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Add author")
    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody @Valid final AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.create(authorRequest));
    }

    @Operation(summary = "Get author by ID")
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable final Long authorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getById(authorId));
    }

    @Operation(summary = "Get all authors")
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getAll());
    }

    @Operation(summary = "Update author by ID")
    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> updateById(
            @PathVariable final Long authorId,
            @RequestBody @Valid final AuthorRequest authorRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.updateById(authorId, authorRequest));
    }

    @Operation(summary = "Delete author by ID")
    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> removeById(@PathVariable final Long authorId) {
        authorService.removeById(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
