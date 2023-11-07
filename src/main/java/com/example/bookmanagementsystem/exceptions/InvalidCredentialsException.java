package com.example.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends CommonException {
    public InvalidCredentialsException(String message){
        super(HttpStatus.BAD_REQUEST,message);
    }
}
