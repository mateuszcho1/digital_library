package com.example.digitallibrary.controller;

import com.example.digitallibrary.model.request.BookRequest;
import com.example.digitallibrary.model.response.BookResponse;
import com.example.digitallibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Add book")
    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid final BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.create(bookRequest));
    }

    @Operation(summary = "Get book by ID")
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getById(@PathVariable final Long bookId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getById(bookId));
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getAll());
    }

    @Operation(summary = "Update book by ID")
    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateById(
            @PathVariable final Long bookId,
            @RequestBody @Valid final BookRequest bookRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.updateById(bookId, bookRequest));
    }

    @Operation(summary = "Delete book by ID")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> removeById(@PathVariable final Long bookId) {
        bookService.removeById(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
