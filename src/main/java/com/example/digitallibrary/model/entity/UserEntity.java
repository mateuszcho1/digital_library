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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "user_entity_id",
            updatable = false
    )
    private long id;

    private String username;
    private String password;

    @ManyToMany(
            cascade = CascadeType.REMOVE
    )
    @Builder.Default
    private List<Book> bookShelf = new ArrayList<>();
}
