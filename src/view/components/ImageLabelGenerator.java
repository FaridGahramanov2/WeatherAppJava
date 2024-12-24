package view.components;

import javax.swing.*;
import java.awt.*;

public class ImageLabelGenerator {
    private final String imagePath;
    private final Rectangle bounds;

    public ImageLabelGenerator(String imagePath, Rectangle bounds) {
        this.imagePath = imagePath;
        this.bounds = bounds;
    }

    public JLabel createImageLabel() {
        JLabel label = new JLabel();
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(
                    bounds.width, bounds.height, Image.SCALE_SMOOTH
            );
            label.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            label.setText("Image not found");
        }
        label.setBounds(bounds);
        return label;
    }
}
