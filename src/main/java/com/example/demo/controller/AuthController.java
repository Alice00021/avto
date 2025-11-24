package com.example.demo.controller;

import com.example.demo.controller.dto.requests.AuthRequest;
import com.example.demo.entity.*;
import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

   @PostMapping("/login")
    public Token login(@RequestBody AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        if ("admin".equals(username) && "12345".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return new Token(token);
        }
        return new Token("Неверный логин или пароль");
    }
}

