package com.example.project.data_processing;
import org.json.JSONObject;

public class OpenFoodFactsDataExtractor {

    public static JSONObject extractProductData(JSONObject productJson) {
        // Create a result object to store the extracted data
        JSONObject result = new JSONObject();

        if (productJson != null && productJson.has("product")) {
            JSONObject product = productJson.getJSONObject("product");

            try {
                // Product details
                String productName = product.optString("product_name", "Unknown product name");
                result.put("Product Name", productName);

                String brand = product.optString("brands", "Unknown brand");
                result.put("Brand", brand);

                // Nutritional information per 1 gram
                if (product.has("nutriments")) {
                    JSONObject nutriments = product.getJSONObject("nutriments");

                    double energy = nutriments.optDouble("energy_100g", -1) / 100;
                    result.put("Energy (kcal per 1g)", energy);

                    double protein = nutriments.optDouble("proteins_100g", -1) / 100;
                    result.put("Protein (g per 1g)", protein);

                    double carbs = nutriments.optDouble("carbohydrates_100g", -1) / 100;
                    result.put("Carbs (g per 1g)", carbs);

                    double fats = nutriments.optDouble("fat_100g", -1) / 100;
                    result.put("Fats (g per 1g)", fats);

                    double calcium = nutriments.optDouble("calcium_100g", -1) / 100;
                    result.put("Calcium (mg per 1g)", calcium);

                    double iron = nutriments.optDouble("iron_100g", -1) / 100;
                    result.put("Iron (mg per 1g)", iron);

                    double magnesium = nutriments.optDouble("magnesium_100g", -1) / 100;
                    result.put("Magnesium (mg per 1g)", magnesium);

                    double phosphorus = nutriments.optDouble("phosphorus_100g", -1) / 100;
                    result.put("Phosphorus (mg per 1g)", phosphorus);

                    double potassium = nutriments.optDouble("potassium_100g", -1) / 100;
                    result.put("Potassium (mg per 1g)", potassium);

                    double sodium = nutriments.optDouble("sodium_100g", -1) / 100;
                    result.put("Sodium (mg per 1g)", sodium);

                    double zinc = nutriments.optDouble("zinc_100g", -1) / 100;
                    result.put("Zinc (mg per 1g)", zinc);

                    double vitaminA = nutriments.optDouble("vitamin-a_100g", -1) / 100;
                    result.put("Vitamin A (µg per 1g)", vitaminA);

                    double vitaminC = nutriments.optDouble("vitamin-c_100g", -1) / 100;
                    result.put("Vitamin C (mg per 1g)", vitaminC);

                    double vitaminD = nutriments.optDouble("vitamin-d_100g", -1) / 100;
                    result.put("Vitamin D (µg per 1g)", vitaminD);

                    double vitaminE = nutriments.optDouble("vitamin-e_100g", -1) / 100;
                    result.put("Vitamin E (mg per 1g)", vitaminE);

                    double vitaminK = nutriments.optDouble("vitamin-k_100g", -1) / 100;
                    result.put("Vitamin K (µg per 1g)", vitaminK);

                    double vitaminB1 = nutriments.optDouble("vitamin-b1_100g", -1) / 100;
                    result.put("Vitamin B1 (Thiamin) (mg per 1g)", vitaminB1);

                    double vitaminB2 = nutriments.optDouble("vitamin-b2_100g", -1) / 100;
                    result.put("Vitamin B2 (Riboflavin) (mg per 1g)", vitaminB2);

                    double vitaminB3 = nutriments.optDouble("niacin_100g", -1) / 100;
                    result.put("Vitamin B3 (Niacin) (mg per 1g)", vitaminB3);

                    double vitaminB6 = nutriments.optDouble("vitamin-b6_100g", -1) / 100;
                    result.put("Vitamin B6 (mg per 1g)", vitaminB6);

                    double vitaminB9 = nutriments.optDouble("folate_100g", -1) / 100;
                    result.put("Vitamin B9 (Folate) (µg per 1g)", vitaminB9);

                    double vitaminB12 = nutriments.optDouble("vitamin-b12_100g", -1) / 100;
                    result.put("Vitamin B12 (µg per 1g)", vitaminB12);

                    double fiber = nutriments.optDouble("fiber_100g", -1) / 100;
                    result.put("Fiber (g per 1g)", fiber);

                    double cholesterol = nutriments.optDouble("cholesterol_100g", -1) / 100;
                    result.put("Cholesterol (mg per 1g)", cholesterol);

                    double omega3 = nutriments.optDouble("omega-3_100g", -1) / 100;
                    result.put("Omega-3 Fatty Acids (g per 1g)", omega3);

                    double omega6 = nutriments.optDouble("omega-6_100g", -1) / 100;
                    result.put("Omega-6 Fatty Acids (g per 1g)", omega6);

                    double omega9 = nutriments.optDouble("omega-9_100g", -1) / 100;
                    result.put("Omega-9 Fatty Acids (g per 1g)", omega9);

                }
            } catch (Exception e) {
                // In case of an error, add an error message to the result object
                result.put("Error", "Error extracting product data: " + e.getMessage());
            }
        } else {
            // If no product information found, add a relevant message to the result object
            result.put("Error", "No product information found.");
        }

        return result;
    }
}



