package UI;

import com.github.sarxos.webcam.WebcamPanel;
import barcode.BarcodeDecodingLaunchPoint;
import util.WebcamBarcodeScannerUtil;

import javax.swing.*;
import java.awt.*;

public class ScannerUI extends JFrame {

    private JPanel MainPanel;
    private JButton takePictureButton;
    private final WebcamBarcodeScannerUtil scanner;

    public ScannerUI() {
        scanner = new WebcamBarcodeScannerUtil();
        setupUI();
    }

    private void setupUI() {
        WebcamPanel webcamPanel = new WebcamPanel(scanner.getWebcam());
        webcamPanel.setLayout(new BorderLayout());

        // Create an instance of OverlayPanel
        OverlayPanel overlay = new OverlayPanel();
        overlay.setPreferredSize(webcamPanel.getPreferredSize());

        // Create a JLayeredPane to manage the overlay
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(webcamPanel.getPreferredSize());

        // Add both the webcam panel and the overlay panel to the layered pane
        webcamPanel.setBounds(0, 0, webcamPanel.getPreferredSize().width, webcamPanel.getPreferredSize().height);
        overlay.setBounds(0, 0, webcamPanel.getPreferredSize().width, webcamPanel.getPreferredSize().height);
        layeredPane.add(webcamPanel, Integer.valueOf(1));
        layeredPane.add(overlay, Integer.valueOf(2));

        JFrame frame = new JFrame("ScannerUI");
        frame.add(MainPanel).isBackgroundSet();
        frame.add(layeredPane, BorderLayout.SOUTH); // Use the layered pane

        takePictureButton.addActionListener(e -> {
            try {
                new BarcodeDecodingLaunchPoint(scanner);
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
