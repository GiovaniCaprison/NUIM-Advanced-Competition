// src/main/java/com/example/project/controller/FoodDiaryController.java
package com.example.project.controller;

import com.example.project.model.Barcode;
import com.example.project.service.BarcodeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BarcodeEntryController {
    @Autowired
    private BarcodeEntryService barcodeEntryService;

    @PostMapping("/barcodeEntry")
    public ResponseEntity<?> createBarcodeEntry(@RequestBody Barcode barcodeEntry, @RequestParam String userEmail) {
        try {
            barcodeEntryService.createBarcodeEntry(barcodeEntry, userEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body("FoodDiary created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating FoodDiary: " + e.getMessage());
        }
    }
}