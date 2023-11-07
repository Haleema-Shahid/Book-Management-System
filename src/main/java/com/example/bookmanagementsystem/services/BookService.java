package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.dtos.BookRequestDTO;
import com.example.bookmanagementsystem.dtos.BookResponseDTO;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Integer id);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO getBookByTitle(String title);
}
