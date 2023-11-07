package com.example.bookmanagementsystem.services;


import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorEntityDTO> getAllAuthors();
    AuthorEntityDTO getAuthorById(Integer id);
    AuthorEntityDTO createAuthor(AuthorEntityDTO authorEntityDTO);
    AuthorEntityDTO updateAuthor(AuthorEntityDTO authorEntityDTO);
}
