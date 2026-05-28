package com.airlines.demo.AirlinesManagement.controller;

import com.airlines.demo.AirlinesManagement.dto.AuthLogin;
import com.airlines.demo.AirlinesManagement.repository.UserRepository;
import com.airlines.demo.AirlinesManagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public String authValid(@RequestBody AuthLogin loginRequest){
        return jwtService.generateToken(loginRequest.getUsername());
    }
}
