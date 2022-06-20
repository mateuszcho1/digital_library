package com.example.digitallibrary.service;

import com.example.digitallibrary.mapper.AuthorMapper;
import com.example.digitallibrary.model.entity.Author;
import com.example.digitallibrary.model.request.AuthorRequest;
import com.example.digitallibrary.model.response.AuthorResponse;
import com.example.digitallibrary.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponse create(final AuthorRequest authorRequest) {
        Author author = AuthorMapper.map(authorRequest);
        authorRepository.findByFirstnameAndLastname(
                authorRequest.getFirstname(),
                authorRequest.getLastname()
        ).ifPresent(a -> {
            throw new EntityExistsException();
        });
        return AuthorMapper.map(authorRepository.save(author));
    }

    public AuthorResponse getById(final Long authorId) {
        return AuthorMapper.map(getAuthorByIdFromDatabase(authorId));
    }

    public List<AuthorResponse> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::map)
                .collect(Collectors.toList());
    }

    public AuthorResponse updateById(
            final Long authorId,
            final AuthorRequest authorRequest
    ) {
        final Author authorFromDatabase = getAuthorByIdFromDatabase(authorId);
        authorFromDatabase.setFirstname(authorRequest.getFirstname());
        authorFromDatabase.setLastname(authorRequest.getLastname());
        return AuthorMapper.map(authorRepository.save(authorFromDatabase));
    }

    public void removeById(final Long authorId) {
        getAuthorByIdFromDatabase(authorId);
        authorRepository.deleteById(authorId);
    }

    private Author getAuthorByIdFromDatabase(final Long authorId) {
        final Optional<Author> authorFromDatabase = authorRepository.findById(authorId);
        return authorFromDatabase.orElseThrow(EntityNotFoundException::new);
    }
}
