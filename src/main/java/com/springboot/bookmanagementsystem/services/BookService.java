package com.springboot.bookmanagementsystem.services;

import com.springboot.bookmanagementsystem.dtos.BookRequestDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Integer id);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO getBookByTitle(String title);
}
