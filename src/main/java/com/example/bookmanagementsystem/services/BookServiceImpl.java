package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.repositories.AuthorRepository;
import com.example.bookmanagementsystem.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
}
