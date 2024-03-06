package UI;

import javax.swing.*;
import java.awt.*;

class OverlayPanel extends JPanel {
    public OverlayPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGuideBox(g);
    }

    private void drawGuideBox(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke and color for the box
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.RED);

        // Calculate the position and size of the box
        int x = this.getWidth()/3;
        int y = this.getHeight()/3;
        int width = this.getWidth() - 400;
        int height = this.getHeight() - 350;

        // Draw the box
        g2d.drawRect(x, y, width, height);

        g2d.dispose();
    }
}

