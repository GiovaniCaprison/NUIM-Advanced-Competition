// src/main/java/com/example/project/model/User.java
package com.example.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean allowExtraEmails;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isAllowExtraEmails() { return allowExtraEmails; }
    public void setAllowExtraEmails(boolean allowExtraEmails) { this.allowExtraEmails = allowExtraEmails; }
}
