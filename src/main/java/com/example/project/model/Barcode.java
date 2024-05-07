// src/main/java/com/example/project/model/FoodDiary.java
package com.example.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "barcodeitems")
public class Barcode {

    private String userEmail;
    private String barcode;
    private Object response;

    @DBRef
    private User user;

    // Getters and Setters
    public String getId() {
        return userEmail;
    }

    public void setId(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}