package component;

import javax.swing.*;

import java.awt.*;

public class RoundedPanel extends JPanel {

    private int cornerRadius;

    public RoundedPanel(int radius) {

        super();

        cornerRadius = radius;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // SHADOW

        g2.setColor(
                new Color(0, 0, 0, 20)
        );

        g2.fillRoundRect(
                5,
                5,
                getWidth() - 10,
                getHeight() - 10,
                cornerRadius,
                cornerRadius
        );

        // MAIN BACKGROUND

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 10,
                getHeight() - 10,
                cornerRadius,
                cornerRadius
        );

        g2.dispose();

        super.paintComponent(g);
    }
}