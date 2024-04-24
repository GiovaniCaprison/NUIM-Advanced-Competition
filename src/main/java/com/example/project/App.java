package com.example.project;

import com.example.project.controller.DecodedBarcodeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
    