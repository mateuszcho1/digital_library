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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "book_id",
            updatable = false
    )
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "id")
    private Author author;

    @ManyToMany(
            cascade = CascadeType.REMOVE
    )
    @Builder.Default
    private List<UserEntity> users = new ArrayList<>();

    private int publishingYear;
    private String bookType;

    @Builder.Default
    private Boolean isTaken = false;
}
