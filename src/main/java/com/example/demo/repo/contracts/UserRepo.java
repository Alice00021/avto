package com.example.demo.repo.contracts;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> getByEmail(String email);
}

