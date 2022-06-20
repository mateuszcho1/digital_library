package com.example.digitallibrary.service;

import com.example.digitallibrary.mapper.UserMapper;
import com.example.digitallibrary.model.entity.Book;
import com.example.digitallibrary.model.entity.UserEntity;
import com.example.digitallibrary.model.request.UserRequest;
import com.example.digitallibrary.model.response.UserResponse;
import com.example.digitallibrary.repository.BookRepository;
import com.example.digitallibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserResponse create(final UserRequest userRequest) {
        UserEntity user = UserMapper.map(userRequest);
        userRepository.findByUsername(userRequest.getUsername()).ifPresent(u -> {
            throw new EntityExistsException();
        });
        return UserMapper.map(userRepository.save(user));
    }

    public UserResponse addBookToBookShelf(final String username, final String title) {
        final UserEntity userFromDatabase = getUserByUsernameFromDatabase(username);
        Book book = bookRepository.findByTitle(title).orElseThrow(EntityNotFoundException::new);
        if (book.getIsTaken()) {
            throw new EntityExistsException();
        }
        book.setIsTaken(true);
        List<UserEntity> users = book.getUsers();
        users.add(userFromDatabase);
        List<Book> books = userFromDatabase.getBookShelf();
        books.add(book);
        userFromDatabase.setBookShelf(books);
        return UserMapper.map(userRepository.save(userFromDatabase));
    }

    public UserResponse removeBookFromBookShelf(final String username, final String title) {
        final UserEntity userFromDatabase = getUserByUsernameFromDatabase(username);
        Book book = bookRepository.findByTitle(title).orElseThrow(EntityNotFoundException::new);
        if (!book.getIsTaken()) {
            throw new EntityExistsException();
        }
        book.setIsTaken(false);
        List<UserEntity> users = book.getUsers();
        users.remove(userFromDatabase);
        List<Book> books = userFromDatabase.getBookShelf();
        books.remove(book);
        userFromDatabase.setBookShelf(books);
        return UserMapper.map(userRepository.save(userFromDatabase));
    }

    public UserResponse getByUsername(final String username) {
        return UserMapper.map(getUserByUsernameFromDatabase(username));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }

    public UserResponse updateById(
            final Long userId,
            final UserRequest userRequest
    ) {
        final UserEntity userFromDatabase = getUserByIdFromDatabase(userId);
        userFromDatabase.setUsername(userRequest.getUsername());
        userFromDatabase.setPassword(userRequest.getPassword());
        return UserMapper.map(userRepository.save(userFromDatabase));
    }

    public void removeById(final Long userId) {
        getUserByIdFromDatabase(userId);
        userRepository.deleteById(userId);
    }

    private UserEntity getUserByIdFromDatabase(final Long userId) {
        final Optional<UserEntity> userFromDatabase = userRepository.findById(userId);
        return userFromDatabase.orElseThrow(EntityNotFoundException::new);
    }

    private UserEntity getUserByUsernameFromDatabase(final String username) {
        final Optional<UserEntity> userFromDatabase = userRepository.findByUsername(username);
        return userFromDatabase.orElseThrow(EntityNotFoundException::new);
    }
}
