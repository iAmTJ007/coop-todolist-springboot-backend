package com.example.authservice.controller;

import com.example.authservice.dto.*;
import com.example.authservice.entity.User;
import com.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    AuthController(AuthService authService){
        this.authService=authService;
    }

    //register
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        User user= User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        return authService.register(user);
    }

    //login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        String token=authService.login(loginRequest.getUsername(),loginRequest.getPassword());
        return new AuthResponse(token);
    }
}
