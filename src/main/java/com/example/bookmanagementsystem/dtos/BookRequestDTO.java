package com.example.bookmanagementsystem.dtos;

import com.example.bookmanagementsystem.entities.AuthorEntity;
import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private int id;
    private String title;
    private String isbn;
    private Date publicationDate;
    private String summary;
    private Integer authorByAuthorId;

}
