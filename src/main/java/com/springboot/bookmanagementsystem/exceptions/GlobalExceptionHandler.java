package com.springboot.bookmanagementsystem.exceptions;

import com.springboot.bookmanagementsystem.dtos.ResponseEntityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseEntityDTO<Void>> handleCustomException(CommonException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ResponseEntityDTO<>(null, ex.getMessage(), ex.getHttpStatus()));
    }

}
