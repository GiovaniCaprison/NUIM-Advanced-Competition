// src/main/java/com/example/project/repository/UserRepository.java
package com.example.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.project.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Additional custom queries can be added here if needed
}
