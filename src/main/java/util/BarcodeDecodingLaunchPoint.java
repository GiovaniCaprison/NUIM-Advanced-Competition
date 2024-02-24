package util;

import imageProcessing.ImagePreprocessing;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BarcodeDecodingLaunchPoint {

    public BarcodeDecodingLaunchPoint(BufferedImage image) throws IOException {
        BufferedImage processedImage = preprocessImage(image);
        // Continue with barcode decoding using the processedImage
    }

    public BufferedImage preprocessImage(BufferedImage image) {
        BufferedImage grayscaleImage = ImagePreprocessing.toGrayscale(image);
        return ImagePreprocessing.applyBinarization(grayscaleImage, 128);
        // Add additional preprocessing steps as needed
    }
}
