package com.example.bookmanagementsystem.dtos;


import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntityDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String biography;
}
