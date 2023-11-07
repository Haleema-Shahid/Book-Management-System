package com.springboot.bookmanagementsystem.services;

import com.springboot.bookmanagementsystem.entities.UserEntity;
import com.springboot.bookmanagementsystem.enums.Role;

public interface UserService {
    String userLogin(String email, String password) throws Exception;

    UserEntity userSignup(String email, String password, Role roleEnum);
}
