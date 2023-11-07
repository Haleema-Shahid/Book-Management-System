package com.springboot.bookmanagementsystem.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    String email;
    String password;
}
