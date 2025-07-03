package com.example.auth_service.web;

import com.example.auth_service.model.AuthRequest;
import com.example.auth_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    // Simple hardcoded authentication
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        if ("user".equals(authRequest.getUsername()) && "pass".equals(authRequest.getPassword())) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

