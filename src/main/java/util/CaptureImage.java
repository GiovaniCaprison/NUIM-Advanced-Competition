package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureImage {

    public static void captureImage(BarcodeScanner scanner) throws IOException {
        BufferedImage image = scanner.getWebcam().getImage();
        // Crop the image based on the overlay dimensions
        // Example dimensions, adjust according to your overlay size and position
        int cropX = image.getWidth() / 3;
        int cropY = image.getHeight() / 3;
        int cropWidth = image.getWidth() - 400; // Adjust based on actual overlay size
        int cropHeight = image.getHeight() - 350; // Adjust based on actual overlay size

        BufferedImage croppedImage = image.getSubimage(cropX, cropY, cropWidth, cropHeight);
        transporterToBarcodeDecoding(croppedImage);
    }


    public static void transporterToBarcodeDecoding(BufferedImage image) throws IOException { new BarcodeDecodingLaunchPoint(image); }
}
