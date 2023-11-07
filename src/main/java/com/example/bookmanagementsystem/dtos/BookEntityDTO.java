package com.example.bookmanagementsystem.dtos;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntityDTO {
    private int id;
    private String title;
    private String isbn;
    private Date publicationDate;
    private String summary; //should have 500 chars max
    private AuthorEntityDTO authorByAuthorId;
}
