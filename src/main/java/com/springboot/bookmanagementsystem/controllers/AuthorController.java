package com.springboot.bookmanagementsystem.controllers;

import com.springboot.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.springboot.bookmanagementsystem.dtos.BookResponseDTO;
import com.springboot.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.springboot.bookmanagementsystem.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntityDTO<List<AuthorEntityDTO>> getAllAuthors()
    {
        List<AuthorEntityDTO> authorEntityDTOS = authorService.getAllAuthors();
        return new ResponseEntityDTO<>(authorEntityDTOS, "All Authors List!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntityDTO<AuthorEntityDTO> getAuthorById(@PathVariable Integer id)
    {
        AuthorEntityDTO authorEntityDTO = authorService.getAuthorById(id);
        return new ResponseEntityDTO<>(authorEntityDTO, "Author by id = " + id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntityDTO<AuthorEntityDTO> createAuthor(@RequestBody AuthorEntityDTO authorEntityDTO)
    {
        authorEntityDTO = authorService.createAuthor(authorEntityDTO);
        return new ResponseEntityDTO<>(authorEntityDTO, "Author Created Successfully!!!", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntityDTO<AuthorEntityDTO> updateAuthor(@RequestBody AuthorEntityDTO authorEntityDTO)
    {
        authorEntityDTO = authorService.updateAuthor(authorEntityDTO);

        return new ResponseEntityDTO<>(authorEntityDTO, "Author Updated Successfully!!!", HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntityDTO<List<BookResponseDTO>> getBooksByAuthor(@PathVariable Integer id)
    {
        List<BookResponseDTO> bookResponseDTOS = authorService.getBooksByAuthor(id);

        return new ResponseEntityDTO<>(bookResponseDTOS, "Books By Author = " + id, HttpStatus.OK);
    }
}
