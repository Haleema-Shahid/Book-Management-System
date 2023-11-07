package com.springboot.bookmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends CommonException {
    public InternalErrorException(String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR,message);
    }
}
