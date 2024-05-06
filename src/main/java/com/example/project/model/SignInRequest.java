// src/main/java/com/example/project/model/SignInRequest.java
package com.example.project.model;

public class SignInRequest {
    private String email;
    private String password;
    private boolean rememberMe; // Added field

    // Constructors
    public SignInRequest() {}

    public SignInRequest(String email, String password, boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe; // Initialize field
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() { // Getter for new field
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) { // Setter for new field
        this.rememberMe = rememberMe;
    }
}