package com.springboot.bookmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseEntityDTO<T> {
    private T data;
    private String message;
    private HttpStatus httpStatus;
}
