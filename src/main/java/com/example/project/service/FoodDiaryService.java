package com.example.project.service;

import com.example.project.model.FoodDiary;
import com.example.project.repository.FoodDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodDiaryService {
    @Autowired
    private FoodDiaryRepository foodDiaryRepository;

    public FoodDiary createFoodDiary(FoodDiary foodDiary){
        return foodDiaryRepository.save(foodDiary);
    }

    public List<FoodDiary> getFoodDiaries(){
        return foodDiaryRepository.findAll();
    }
}
