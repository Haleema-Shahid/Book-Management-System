package com.example.bookmanagementsystem.services;

import com.example.bookmanagementsystem.dtos.UserResponseDto;
import com.example.bookmanagementsystem.entities.UserEntity;
import com.example.bookmanagementsystem.enums.Role;
import com.example.bookmanagementsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    boolean isEmailValid(String email){
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    @Override
    public String userLogin(String email, String password) throws Exception {
        try {
            if(isEmailValid(email)) {
                UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user exists under this email."));
                if (user == null) {
                    //System.out.println("user is null");
                    return null;
                }
                //System.out.println("user fetched: " + user.getEmail());
                String encodedPassword = user.getPassword();
                if (passwordEncoder.matches(password, encodedPassword)) {
                    //System.out.println(token);
                    return jwtService.generateToken(user);
                } //TODO:DOWN HERE, EXCEPTION THROW
                else throw new UsernameNotFoundException("Incorrect Password");
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            //TODO: exception here
            return null;
        }
    }

    @Override
    public UserEntity userSignup(String email, String password, Role roleEnum) {
        try{
            String encodedPassword = passwordEncoder.encode(password);

            Role role=roleEnum;
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(encodedPassword);
            user.setRole(role);

            UserEntity savedUser = userRepository.save(user);
            return savedUser;
        }
        catch(Exception e){
            //TODO: EXCEPTION HERE
            return null;

        }
    }
}
