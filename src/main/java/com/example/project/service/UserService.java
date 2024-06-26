// src/main/java/com/example/project/service/UserService.java
package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
