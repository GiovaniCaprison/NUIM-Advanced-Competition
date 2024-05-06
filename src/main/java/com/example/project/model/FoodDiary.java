package com.example.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fooddiary")
public class FoodDiary {
    @Id
    private String id;

    private String foodName;

    private int calories;


    public FoodDiary(String id, String foodName, int calories){
        this.id = id;
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName(){
        return this.foodName;
    }

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public int getCalories(){
        return this.calories;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }
}
