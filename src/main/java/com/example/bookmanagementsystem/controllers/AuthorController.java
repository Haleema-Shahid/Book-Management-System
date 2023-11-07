package com.example.bookmanagementsystem.controllers;

import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.example.bookmanagementsystem.services.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

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
}
