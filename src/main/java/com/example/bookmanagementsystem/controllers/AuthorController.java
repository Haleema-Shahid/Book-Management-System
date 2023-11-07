package com.example.bookmanagementsystem.controllers;

import com.example.bookmanagementsystem.dtos.AuthorEntityDTO;
import com.example.bookmanagementsystem.dtos.ResponseEntityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping
    public ResponseEntityDTO<Set<AuthorEntityDTO>> getAllAuthors()
    {
        return null;
    }

    
}
