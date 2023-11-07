package com.example.bookmanagementsystem.repositories;

import com.example.bookmanagementsystem.entities.AuthorEntity;
import com.example.bookmanagementsystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findByTitle(String title);
=======
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByAuthorByAuthorId(AuthorEntity authorByAuthorId);
>>>>>>> 68dbcb3b5c84fb9dbaee5d889a65324ccfbd2e90
}
