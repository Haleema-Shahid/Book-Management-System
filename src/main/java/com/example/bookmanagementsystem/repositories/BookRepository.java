package com.example.bookmanagementsystem.repositories;

import com.example.bookmanagementsystem.entities.AuthorEntity;
import com.example.bookmanagementsystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthorByAuthorId(AuthorEntity authorByAuthorId);
}
