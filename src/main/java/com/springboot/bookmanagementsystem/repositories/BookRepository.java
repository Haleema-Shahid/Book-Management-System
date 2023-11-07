package com.springboot.bookmanagementsystem.repositories;

import com.springboot.bookmanagementsystem.entities.AuthorEntity;
import com.springboot.bookmanagementsystem.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthorByAuthorId(AuthorEntity authorByAuthorId);
}
