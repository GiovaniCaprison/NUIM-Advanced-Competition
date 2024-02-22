package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BarcodeEncoder {
    static File path = new File("first2.PNG");
    static Image image;

    static {
        try {
            image = ImageIO.read(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BarcodeEncoder() throws IOException {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        System.out.println("Image Width: " + imageWidth + " Image Height: " + imageHeight);
    }

    public static void main(String[] args) throws IOException {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        System.out.println("Image Width: " + imageWidth + " Image Height: " + imageHeight);
    }
}
