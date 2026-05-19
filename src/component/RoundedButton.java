package component;

import javax.swing.*;

import java.awt.*;

public class RoundedButton extends JButton {

    private Color backgroundColor;

    private int radius = 20;

    public RoundedButton(
            String text,
            Color color
    ) {

        super(text);

        backgroundColor = color;

        setFocusPainted(false);

        setContentAreaFilled(false);

        setBorderPainted(false);

        setForeground(Color.WHITE);

        setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        setFont(
                new Font(
                        "Poppins",
                        Font.BOLD,
                        18
                )
        );
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
                new Color(0, 0, 0, 25)
        );

        g2.fillRoundRect(
                3,
                4,
                getWidth() - 6,
                getHeight() - 4,
                radius,
                radius
        );

        // BUTTON

        g2.setColor(backgroundColor);

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 6,
                getHeight() - 6,
                radius,
                radius
        );

        super.paintComponent(g2);

        g2.dispose();
    }
}