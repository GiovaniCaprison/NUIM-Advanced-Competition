package com.example.project.controller;

import org.springframework.stereotype.Service;
import com.example.project.service.OpenFoodFactsAPI;
//import com.example.project.data_processing.OpenFoodFactsDataExtractor;

@Service
public class ServiceLaunchPoint {
    public String processBarcode(String barcode) {

        var productJson = OpenFoodFactsAPI.getProductByBarcode(barcode);
        assert productJson != null;
        return productJson.toString();
        //OpenFoodFactsDataExtractor.extractProductData(productJson).toString();
    }
}