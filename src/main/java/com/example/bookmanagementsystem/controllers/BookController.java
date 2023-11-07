package com.example.bookmanagementsystem.controllers;

import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.dtos.BookRequestDTO;
import com.example.bookmanagementsystem.dtos.BookResponseDTO;
import com.example.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.example.bookmanagementsystem.services.AuthorServiceImpl;
import com.example.bookmanagementsystem.services.BookService;
import com.example.bookmanagementsystem.services.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntityDTO<List<BookResponseDTO>> getAllBooks()
    {
        List<BookResponseDTO> bookResponseDTOS = bookService.getAllBooks();
        return new ResponseEntityDTO<>(bookResponseDTOS, "All Books List!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntityDTO<BookResponseDTO> getBookById(@PathVariable Integer id)
    {
        BookResponseDTO bookResponseDTOS = bookService.getBookById(id);
        return new ResponseEntityDTO<>(bookResponseDTOS, "Book by id = " + id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntityDTO<BookResponseDTO> createAuthor(@RequestBody BookRequestDTO bookRequestDTO)
    {
        return new ResponseEntityDTO<>(bookService.createBook(bookRequestDTO),
                "Book Created Successfully!!!", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntityDTO<BookResponseDTO> updateAuthor(@RequestBody BookRequestDTO bookRequestDTO)
    {
        return new ResponseEntityDTO<>(bookService.updateBook(bookRequestDTO),
                "Book Updated Successfully!!!", HttpStatus.OK);
    }
}
