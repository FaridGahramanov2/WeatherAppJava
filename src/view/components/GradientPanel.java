package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * GradientPanel is a custom JPanel that allows for gradient backgrounds.
 */
public class GradientPanel extends JPanel {
    private final Color startColor;
    private final Color endColor;

    /**
     * Constructor to create a panel with gradient colors.
     * @param startColor The starting color of the gradient.
     * @param endColor The ending color of the gradient.
     */
    public GradientPanel(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smooth gradient
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw gradient background
        GradientPaint gradientPaint = new GradientPaint(
                0, 0, startColor, getWidth(), getHeight(), endColor
        );
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}