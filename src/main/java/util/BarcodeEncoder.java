package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BarcodeEncoder {
    private Image image;

    public BarcodeEncoder(String imagePath) throws IOException {
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) throws IOException {
        this.image = ImageIO.read(new File(imagePath));
    }

    public void encodeBarcode() {
        // Placeholder for barcode encoding logic
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        System.out.println("Image Width: " + imageWidth + " Image Height: " + imageHeight);
    }
}
