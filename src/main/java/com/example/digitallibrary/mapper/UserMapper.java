package com.example.digitallibrary.mapper;

import com.example.digitallibrary.model.entity.Book;
import com.example.digitallibrary.model.entity.UserEntity;
import com.example.digitallibrary.model.request.UserRequest;
import com.example.digitallibrary.model.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserEntity map(final UserRequest userRequest) {
        return UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
    }

    public static UserResponse map(final UserEntity user) {
        List<String> bookShelf = user.getBookShelf()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .bookShelf(bookShelf)
                .build();
    }
}
