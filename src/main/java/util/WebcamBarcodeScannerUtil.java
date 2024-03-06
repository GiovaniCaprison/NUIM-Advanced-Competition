package util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamBarcodeScannerUtil {
    private final Webcam webcam;

    public WebcamBarcodeScannerUtil() {
        webcam = Webcam.getDefault();
        initializeWebcam();
    }

    private void initializeWebcam() {
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
    }

    public Webcam getWebcam() {
        return webcam;
    }

    public void closeWebcam() {
        if (webcam.isOpen()) {
            webcam.close();
        }
    }
}
