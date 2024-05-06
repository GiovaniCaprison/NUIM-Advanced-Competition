// src/main/java/com/example/project/controller/UserController.java
package com.example.project.controller;

import com.example.project.model.SignInRequest;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and Password are required");
        }

        // Hash the password before storing
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        if (signInRequest.getEmail() == null || signInRequest.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and Password are required");
        }

        User foundUser = userService.findUserByEmail(signInRequest.getEmail());

        if (foundUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else if (!passwordEncoder.matches(signInRequest.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Generate a token or session (implementation depends on your security setup)
        return ResponseEntity.ok("Sign-in successful");
    }
}
