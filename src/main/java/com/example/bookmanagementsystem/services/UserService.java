package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.dtos.UserResponseDto;
import com.example.bookmanagementsystem.entities.UserEntity;
import com.example.bookmanagementsystem.enums.Role;

public interface UserService {
    String userLogin(String email, String password) throws Exception;

    UserEntity userSignup(String email, String password, Role roleEnum);
}
