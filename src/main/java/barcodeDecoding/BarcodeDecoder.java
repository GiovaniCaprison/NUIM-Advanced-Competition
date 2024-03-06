package barcodeDecoding;

import java.awt.image.BufferedImage;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeDecoder {
    public String getDecodedBarcode(BufferedImage image) {
        do{
            try{
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);
                if(result.getText() != null) {
                    System.out.println(result.getText());
                    break;
                }
            } catch (NotFoundException e){
                System.out.println("Result Library: " + e);
            }
        } while(true);
        return null;
    }
}
