// src/main/java/com/example/project/repository/UserRepository.java
package com.example.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

