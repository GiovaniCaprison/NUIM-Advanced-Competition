package util;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptureImage {

    public static void captureImage(BarcodeScanner scanner) throws IOException {
        BufferedImage image = scanner.getWebcam().getImage();
        transporterToBarcodeDecoding(image);
    }

    public static void transporterToBarcodeDecoding(BufferedImage image) throws IOException { new BarcodeDecodingLaunchPoint(image); }
}
