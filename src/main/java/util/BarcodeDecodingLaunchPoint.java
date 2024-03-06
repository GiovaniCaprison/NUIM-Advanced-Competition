package util;

import barcodeDecoding.BarcodeDecoder;

import java.awt.image.BufferedImage;

public class BarcodeDecodingLaunchPoint {

    public BarcodeDecodingLaunchPoint(BufferedImage image) {
        String decodedBarcode = processImage(image);
    }

    private String processImage(BufferedImage image) {
        return new BarcodeDecoder().getDecodedBarcode(image);
    }
}
