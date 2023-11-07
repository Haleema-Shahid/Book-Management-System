package com.springboot.bookmanagementsystem.dtos;

import com.springboot.bookmanagementsystem.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    int id;
    String email;
    Role role;
}
