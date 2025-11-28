package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.EmailAlreadyUsedException;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.repo.persistent.UserPostgres;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserPostgres userRepo;
    private final PasswordEncoder encoder;

    public AuthService(UserPostgres userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User register(User user, User newUser){

        if(user.getRole() == UserRole.ADMIN){
          throw new AccessDeniedException(); 
        }

        if (userRepo.getByEmail(newUser.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        newUser.setPassword(encoder.encode(newUser.getPassword()));

        return userRepo.create(newUser);
    }

    public User getUserByEmail(String email) {
        return userRepo.getByEmail(email).orElse(null);
    }
}