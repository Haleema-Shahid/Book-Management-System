package com.example.bookmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(unique = true)
    private String isbn;
    private Date publicationDate;
    @Column(length = 500)
    private String summary; //should have 500 chars max
    @ManyToOne
    private AuthorEntity authorByAuthorId;
}
