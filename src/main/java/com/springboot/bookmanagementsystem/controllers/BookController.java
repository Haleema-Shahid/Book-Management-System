package com.springboot.bookmanagementsystem.controllers;

import com.springboot.bookmanagementsystem.dtos.BookRequestDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.springboot.bookmanagementsystem.services.BookService;
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
    public ResponseEntityDTO<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> bookResponseDTOS = bookService.getAllBooks();
        return new ResponseEntityDTO<>(bookResponseDTOS, "All Books List!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntityDTO<BookResponseDTO> getBookById(@PathVariable Integer id) {
        BookResponseDTO bookResponseDTOS = bookService.getBookById(id);
        return new ResponseEntityDTO<>(bookResponseDTOS, "Book by id = " + id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntityDTO<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntityDTO<>(bookService.createBook(bookRequestDTO),
                "Book Created Successfully!!!", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntityDTO<BookResponseDTO> updateBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return new ResponseEntityDTO<>(bookService.updateBook(bookRequestDTO),
                "Book Updated Successfully!!!", HttpStatus.OK);
    }
    @GetMapping("/find/title/{title}")
    public ResponseEntityDTO<BookResponseDTO> getBookByTitle(@PathVariable String title)
    {
        return new ResponseEntityDTO<>(bookService.getBookByTitle(title),
                "Book Found Successfully",HttpStatus.OK);
    }

}
