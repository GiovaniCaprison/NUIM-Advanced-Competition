package api;
import org.json.JSONObject;

public class OpenFoodFactsDataExtractor {

    public static void extractProductData(JSONObject productJson) {
        if (productJson != null && productJson.has("product")) {
            JSONObject product = productJson.getJSONObject("product");

            try {
                // Extract product name
                String productName = product.optString("product_name", "Unknown product name");
                System.out.println("Product Name: " + productName);

                // Extract brands
                String brand = product.optString("brands", "Unknown brand");
                System.out.println("Brand: " + brand);

                // Extract quantity
                String quantity = product.optString("quantity", "Unknown quantity");
                System.out.println("Quantity: " + quantity);

                // Extract nutritional information, for example, energy
                if (product.has("nutriments")) {
                    JSONObject nutriments = product.getJSONObject("nutriments");
                    double energy = nutriments.optDouble("energy", -1); // Assuming energy is measured in kcal (check API docs for unit)
                    if (energy != -1) {
                        System.out.println("Energy: " + energy + " kcal");
                    } else {
                        System.out.println("Energy information is not available.");
                    }
                }


            } catch (Exception e) {
                System.out.println("Error extracting product data: " + e.getMessage());
            }
        } else {
            System.out.println("No product information found.");
        }
    }
}

