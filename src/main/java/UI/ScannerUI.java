package UI;

import com.github.sarxos.webcam.*;
import util.BarcodeEncoder;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class ScannerUI extends JFrame{
    private static final Webcam webcam = Webcam.getDefault();

    private JPanel MainPanel;
    private JButton takePictureButton;

    public ScannerUI() {
        takePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ImageIO.write(webcam.getImage(), "PNG", new File("first2.PNG"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    new BarcodeEncoder();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException{
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setImageSizeDisplayed(true);

        panel.setBackground(Color.YELLOW);
        panel.setMirrored(false);
        JFrame frame = new JFrame("ScannerUI");

        frame.add(new ScannerUI().MainPanel).isBackgroundSet();
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();
        frame.pack();
        frame.setVisible(true);


    }
}
