package com.springboot.bookmanagementsystem.services;


import com.springboot.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorEntityDTO> getAllAuthors();
    AuthorEntityDTO getAuthorById(Integer id);
    AuthorEntityDTO createAuthor(AuthorEntityDTO authorEntityDTO);
    AuthorEntityDTO updateAuthor(AuthorEntityDTO authorEntityDTO);

    List<BookResponseDTO> getBooksByAuthor(Integer id);
}
