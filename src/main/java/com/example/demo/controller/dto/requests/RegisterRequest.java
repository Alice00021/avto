package com.example.demo.controller.dto.requests;

import com.example.demo.entity.UserRole;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private UserRole role; 
}
