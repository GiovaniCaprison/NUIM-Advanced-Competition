package UI;

import com.github.sarxos.webcam.WebcamPanel;
import util.BarcodeScanner;
import util.CaptureImage;

import javax.swing.*;
import java.awt.*;

public class ScannerUI extends JFrame {

    private JPanel MainPanel;
    private JButton takePictureButton;
    private final BarcodeScanner scanner;

    public ScannerUI() {
        scanner = new BarcodeScanner();
        setupUI();
    }

    private void setupUI() {
        WebcamPanel webcamPanel = new WebcamPanel(scanner.getWebcam());
        JFrame frame = new JFrame("ScannerUI");
        frame.add(MainPanel).isBackgroundSet();
        frame.add(webcamPanel, BorderLayout.SOUTH);

        takePictureButton.addActionListener(e -> {
            try {
                CaptureImage.captureImage(scanner);
            } catch (Exception ex) {
                ex.printStackTrace();
                // Consider better error handling
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScannerUI::new);
    }
}
