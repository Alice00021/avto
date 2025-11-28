package com.example.demo.controller;

import com.example.demo.controller.dto.requests.AuthRequest;
import com.example.demo.controller.dto.requests.RegisterRequest;
import com.example.demo.entity.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

   @PostMapping("/login")
    public Token login(@RequestBody AuthRequest request) {
        User user = authService.getUserByEmail(request.getUsername());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getEmail());
            return new Token(token);
        }
        return new Token("Неверный логин или пароль");
    }
}

