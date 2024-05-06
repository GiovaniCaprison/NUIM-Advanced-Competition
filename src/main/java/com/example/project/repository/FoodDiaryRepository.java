package com.example.project.repository;

import com.example.project.model.FoodDiary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FoodDiaryRepository extends MongoRepository<FoodDiary, String>{
}
