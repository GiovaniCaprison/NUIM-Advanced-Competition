package com.example.project.service;




import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.example.project.data_processing.OpenFoodFactsDataExtractor;

@Service
public class ServiceLaunchPoint {
    public String processBarcode(String barcode) {
        JSONObject productJson = OpenFoodFactsAPI.getProductByBarcode(barcode);
        JSONObject extractedData = OpenFoodFactsDataExtractor.extractProductData(productJson);
        return extractedData.toString();

    }
}