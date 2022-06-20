package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstnameAndLastname(String firstname, String lastname);
}
