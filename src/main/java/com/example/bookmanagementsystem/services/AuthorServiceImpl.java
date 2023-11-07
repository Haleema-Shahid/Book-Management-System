package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.repositories.AuthorRepository;
import com.example.bookmanagementsystem.repositories.BookRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

}
