package com.example.bookmanagementsystem.dtos;

import com.example.bookmanagementsystem.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    int id;
    String email;
    Enum<Role> role;
}
