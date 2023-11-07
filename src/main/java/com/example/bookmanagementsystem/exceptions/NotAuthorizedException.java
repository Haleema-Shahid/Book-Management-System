package com.example.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends CommonException{
    public NotAuthorizedException(String message){
        super(HttpStatus.FORBIDDEN,message);
    }
}
