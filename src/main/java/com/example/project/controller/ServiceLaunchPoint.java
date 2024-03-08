package com.example.project.controller;

import org.springframework.stereotype.Service;
import com.example.project.service.OpenFoodFactsAPI;
import com.example.project.data_processing.OpenFoodFactsDataExtractor;

@Service
public class ServiceLaunchPoint {

    public String processBarcode(String barcode) {
        // Implement your logic to process the barcode, e.g., fetch data from OpenFoodFacts
        // This is a simplification; adjust according to your real logic
        var productJson = OpenFoodFactsAPI.getProductByBarcode(barcode);
        OpenFoodFactsDataExtractor.extractProductData(productJson);
        return productJson.toString(); // Adjust based on your requirements
    }
}