package com.springboot.bookmanagementsystem.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserPostDto {
    String email;
    String password;
    String role;
}
