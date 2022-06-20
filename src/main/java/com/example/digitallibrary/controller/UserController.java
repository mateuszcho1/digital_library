package com.example.digitallibrary.controller;

import com.example.digitallibrary.model.request.UserRequest;
import com.example.digitallibrary.model.response.UserResponse;
import com.example.digitallibrary.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Add user")
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid final UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userRequest));
    }

    @Operation(summary = "Get user by username")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable final String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getByUsername(username));
    }

    @Operation(summary = "Add book to user")
    @PutMapping("/{username}/{title}")
    public ResponseEntity<UserResponse> addBookToUser(
            @PathVariable final String username,
            @PathVariable final String title
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.addBookToBookShelf(username, title));
    }

    @Operation(summary = "Delete book from user")
    @DeleteMapping("/{username}/{title}")
    public ResponseEntity<UserResponse> removeBookFromUser(
            @PathVariable final String username,
            @PathVariable final String title
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.removeBookFromBookShelf(username, title));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAll());
    }

    @Operation(summary = "Update user by ID")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateById(
            @PathVariable final Long userId,
            @RequestBody @Valid final UserRequest userRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.updateById(userId, userRequest));
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeById(@PathVariable final Long userId) {
        userService.removeById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
