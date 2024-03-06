package barcode;

import api.OpenFoodFactsAPI;
import api.OpenFoodFactsDataExtractor;
import org.json.JSONObject;
import util.WebcamBarcodeScannerUtil;

public class BarcodeDecodingLaunchPoint {

    private WebcamBarcodeScannerUtil scanner;

    public BarcodeDecodingLaunchPoint(WebcamBarcodeScannerUtil scanner) {
        this.scanner = scanner;
        processImage();
    }

    private void processImage() {
        BarcodeDecoder decoder = new BarcodeDecoder();
        String decodedBarcode = decoder.getDecodedBarcode(scanner);

        if (decodedBarcode != null && !decodedBarcode.isEmpty()) {
            JSONObject productJson = OpenFoodFactsAPI.getProductByBarcode(decodedBarcode);
            if (productJson != null) {
                OpenFoodFactsDataExtractor.extractProductData(productJson);
            } else {
                System.out.println("No data returned from Open Food Facts API for barcode: " + decodedBarcode);
            }
        } else {
            System.out.println("Failed to decode barcode or barcode is empty.");
        }
    }
}


