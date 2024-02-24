package util;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptureImage {

    public static void captureImage(BarcodeScanner scanner) throws IOException {
        BufferedImage image = scanner.getWebcam().getImage();
        saveImage(image);
    }

    public static void saveImage(BufferedImage image) throws IOException { new BarcodeDecodingLaunchPoint(image); }
}
