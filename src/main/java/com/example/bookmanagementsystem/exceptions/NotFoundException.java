package com.example.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CommonException{
    public NotFoundException(String message){
        super(HttpStatus.NOT_FOUND,message);
    }
}
