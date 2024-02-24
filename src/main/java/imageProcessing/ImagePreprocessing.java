package imageProcessing;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class ImagePreprocessing {

    // Convert image to grayscale
    public static BufferedImage toGrayscale(BufferedImage original) {
        BufferedImage grayscaleImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayscaleImage.createGraphics();
        g2d.drawImage(original, 0, 0, null);
        g2d.dispose();
        return grayscaleImage;
    }

    // Apply binarization (thresholding)
    public static BufferedImage applyBinarization(BufferedImage grayscale, int threshold) {
        BufferedImage binarized = new BufferedImage(grayscale.getWidth(), grayscale.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < grayscale.getHeight(); y++) {
            for (int x = 0; x < grayscale.getWidth(); x++) {
                Color color = new Color(grayscale.getRGB(x, y));
                int red = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int binaryValue = 0;
                if (red > threshold) binaryValue = 255;
                Color binaryColor = new Color(binaryValue, binaryValue, binaryValue);
                binarized.setRGB(x, y, binaryColor.getRGB());
            }
        }
        return binarized;
    }

    // You can add more preprocessing methods here, such as edge detection or noise reduction as needed.
}

