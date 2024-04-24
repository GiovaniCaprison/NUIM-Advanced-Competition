package com.example.project.service;

import org.json.JSONObject;
import com.example.project.util.HttpUtil;

public class OpenFoodFactsAPI {

    private static final String BASE_URL = "https://world.openfoodfacts.org/api/v0/product/";

    public static JSONObject getProductByBarcode(String barcode) {
        try {
            String url = BASE_URL + barcode + ".json";
            String response = HttpUtil.sendGetRequest(url);
            return new JSONObject(response);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Consider more robust error handling
        }
    }
}

