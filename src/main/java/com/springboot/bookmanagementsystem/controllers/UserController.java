package com.springboot.bookmanagementsystem.controllers;

import com.springboot.bookmanagementsystem.dtos.ResponseEntityDTO;
import com.springboot.bookmanagementsystem.dtos.UserLoginDto;
import com.springboot.bookmanagementsystem.dtos.UserPostDto;
import com.springboot.bookmanagementsystem.dtos.UserResponseDto;
import com.springboot.bookmanagementsystem.entities.UserEntity;
import com.springboot.bookmanagementsystem.enums.Role;
import com.springboot.bookmanagementsystem.services.JWTService;
import com.springboot.bookmanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
