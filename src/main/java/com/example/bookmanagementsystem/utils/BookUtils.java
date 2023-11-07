package com.example.bookmanagementsystem.utils;

import com.example.bookmanagementsystem.dtos.BookResponseDTO;
import com.example.bookmanagementsystem.entities.BookEntity;

public class BookUtils {
    public static BookResponseDTO mapBookEntityToDTO(BookEntity bookEntity) {
        return BookResponseDTO.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .isbn(bookEntity.getIsbn())
                .publicationDate(bookEntity.getPublicationDate())
                .summary(bookEntity.getSummary())
                .authorByAuthorId(AuthorUtils.toAuthorEntityDTO(bookEntity.getAuthorByAuthorId()))
                .build();
    }

}
