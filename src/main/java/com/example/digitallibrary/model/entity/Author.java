package com.example.digitallibrary.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "author_id",
            updatable = false
    )
    private long id;

    private String firstname;
    private String lastname;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.REMOVE
    )
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
