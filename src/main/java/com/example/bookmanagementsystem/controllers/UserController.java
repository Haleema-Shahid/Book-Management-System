package com.example.bookmanagementsystem.controllers;

import com.example.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.example.bookmanagementsystem.dtos.UserLoginDto;
import com.example.bookmanagementsystem.dtos.UserPostDto;
import com.example.bookmanagementsystem.dtos.UserResponseDto;
import com.example.bookmanagementsystem.entities.UserEntity;
import com.example.bookmanagementsystem.enums.Role;
import com.example.bookmanagementsystem.services.JWTService;
import com.example.bookmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntityDTO<String> login(@RequestBody UserLoginDto userLoginDto) throws Exception {

        String response ="Error occured during login...";

        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        response = userService.userLogin(email, password);
        if(response!=null){
            return new ResponseEntityDTO<>(response, "login successful!", HttpStatus.OK);
        }
        else{
            return new ResponseEntityDTO<>(response, "login unsuccessful!", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/test")
    public String test() {
        return "Testing";
    }
    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody UserPostDto userPostDto) throws Exception {
        String email = userPostDto.getEmail();
        String password = userPostDto.getPassword();
        String role = userPostDto.getRole();
        Role roleEnum = Role.valueOf(role);

        UserEntity userResponse = userService.userSignup(email, password, roleEnum);

        UserResponseDto userResponseDto =  modelMapper.map(userResponse, UserResponseDto.class);
        userResponseDto.setRole(userResponseDto.getRole());
        return userResponseDto;
//        if(userResponse != null){
//            return new ResponseEntityDTO<UserResponseDto>(userResponse, "signup successful!", HttpStatus.CREATED);
//        }
//        else{
//            return new ResponseEntityDTO<UserResponseDto>(null, "signup unsuccessful!", HttpStatus.BAD_REQUEST);
//        }
    }


}
