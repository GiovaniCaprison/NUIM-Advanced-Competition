package com.example.project.controller;

import com.example.project.model.FoodDiary;
import com.example.project.service.FoodDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/fooddiary")
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend's origin
public class FoodDiaryController {
    @Autowired
    private FoodDiaryService foodDiaryService;

    @GetMapping
    public List<FoodDiary> getAllEntries() {
        return foodDiaryService.getFoodDiaries();
    }
}