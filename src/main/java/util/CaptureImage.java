package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureImage {
    public static BufferedImage captureImage(BarcodeScanner scanner) throws IOException {
        return scanner.getWebcam().getImage();
    }

    public static void saveImage(BufferedImage image, String filePath) throws IOException {
        ImageIO.write(image, "PNG", new File(filePath));
    }
}
