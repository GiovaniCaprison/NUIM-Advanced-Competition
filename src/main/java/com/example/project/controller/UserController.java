// src/main/java/com/example/project/controller/UserController.java
package com.example.project.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        // Hash the password before storing
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user");
        }
    }
}
