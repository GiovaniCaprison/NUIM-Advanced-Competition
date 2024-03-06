package barcode;

import java.awt.image.BufferedImage;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import util.WebcamBarcodeScannerUtil;

public class BarcodeDecoder {
    private static final int CROP_RATIO = 3;
    private static final int CROP_WIDTH_REDUCTION = 400;
    private static final int CROP_HEIGHT_REDUCTION = 350;

    public BarcodeDecoder() {}

    public String getDecodedBarcode(WebcamBarcodeScannerUtil scanner) {
        int i = 0;
        do {
            try {
                LuminanceSource source = new BufferedImageLuminanceSource(captureNewImage(scanner));
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);
                if (result.getText() != null) {
                    System.out.println(result.getText());
                    return result.getText();
                }
            } catch (NotFoundException e) {
                System.out.println("Barcode not found in image " + i++);
            }
        } while (true);
    }

    private static BufferedImage captureNewImage(WebcamBarcodeScannerUtil scanner) {
        BufferedImage image = scanner.getWebcam().getImage();
        int cropX = image.getWidth() / CROP_RATIO;
        int cropY = image.getHeight() / CROP_RATIO;
        int cropWidth = image.getWidth() - CROP_WIDTH_REDUCTION;
        int cropHeight = image.getHeight() - CROP_HEIGHT_REDUCTION;
        return image.getSubimage(cropX, cropY, cropWidth, cropHeight);
    }
}
