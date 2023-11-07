package com.springboot.bookmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @Column(length = 1000)
    private String biography;
    @OneToMany(mappedBy = "authorByAuthorId", cascade = CascadeType.ALL)
    private Set<BookEntity> booksByAuthorId;
}
