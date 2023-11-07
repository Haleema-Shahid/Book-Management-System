package com.example.bookmanagementsystem.services;


import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.dtos.BookResponseDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorEntityDTO> getAllAuthors();
    AuthorEntityDTO getAuthorById(Integer id);
    AuthorEntityDTO createAuthor(AuthorEntityDTO authorEntityDTO);
    AuthorEntityDTO updateAuthor(AuthorEntityDTO authorEntityDTO);

    List<BookResponseDTO> getBooksByAuthor(Integer id);
}
