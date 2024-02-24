package util;

import imagePreProcessing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarcodeDecodingLaunchPoint {

    public BarcodeDecodingLaunchPoint(BufferedImage image) throws IOException {
        BufferedImage processedImage = preprocessImage(image);
        ImageIO.write(processedImage, "png", new File("processedImage.png"));
    }

    private BufferedImage preprocessImage(BufferedImage image) {
        OtsuBinarizerTechnique binarizer = new OtsuBinarizerTechnique();
        return binarizer.binarize(image);
    }
}
