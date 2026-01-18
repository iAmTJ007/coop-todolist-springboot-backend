package com.example.authservice.service;

import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    public String register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "The user is saved successfully";
    }

    public String login(String username,String password){
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found"));

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("invalid credentials");
        }
        return jwtService.generateToken(username);
    }
}
