package view;

import patterns.observer.WeatherObserver;
import view.components.ImageLabelGenerator;
import view.utility.CountryImageResolver;
import view.utility.WeatherStateResolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WeatherPanel extends JPanel implements WeatherObserver {
    private JLabel weatherIconLabel;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel pressureLabel;
    private JLabel descriptionLabel;
    private JLabel locationLabel;
    private JLabel flagLabel;

    // Custom colors for text
    private static final Color PRIMARY_TEXT = new Color(255, 255, 255);
    private static final Color SECONDARY_TEXT = new Color(255, 255, 255, 200);

    public WeatherPanel() {
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Initialize components with styled fonts and colors
        weatherIconLabel = new JLabel("", JLabel.CENTER);
        weatherIconLabel.setPreferredSize(new Dimension(150, 150));

        locationLabel = createStyledLabel("", 24, PRIMARY_TEXT, true);
        flagLabel = new JLabel("", JLabel.CENTER);
        temperatureLabel = createStyledLabel("", 48, PRIMARY_TEXT, true);
        descriptionLabel = createStyledLabel("", 18, PRIMARY_TEXT, false);

        // Create details panel for humidity and pressure
        JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        detailsPanel.setOpaque(false);

        humidityLabel = createStyledLabel("", 16, SECONDARY_TEXT, false);
        pressureLabel = createStyledLabel("", 16, SECONDARY_TEXT, false);

        detailsPanel.add(humidityLabel);
        detailsPanel.add(pressureLabel);

        // Layout components
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(weatherIconLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(locationLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(flagLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 5, 10);
        add(temperatureLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 20, 10);
        add(descriptionLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(detailsPanel, gbc);

        setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private JLabel createStyledLabel(String text, int fontSize, Color color, boolean bold) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, fontSize));
        label.setForeground(color);
        return label;
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description, String countryCode) {
        System.out.println("WeatherPanel: Received update");
        SwingUtilities.invokeLater(() -> {
            try {
                // Update weather icon with larger size
                String iconPath = WeatherStateResolver.getWeatherImagePath(description);
                ImageLabelGenerator weatherIconGenerator = new ImageLabelGenerator(iconPath, new Rectangle(0, 0, 150, 150));
                weatherIconLabel.setIcon(weatherIconGenerator.createImageLabel().getIcon());

                // Update flag with appropriate size
                String flagPath = CountryImageResolver.getCountryImagePath(countryCode);
                ImageIcon flagIcon = new ImageIcon(flagPath);
                Image scaledFlag = flagIcon.getImage().getScaledInstance(32, 24, Image.SCALE_SMOOTH);
                flagLabel.setIcon(new ImageIcon(scaledFlag));

                // Update text labels with formatted values
                locationLabel.setText(String.format("%s", countryCode.toUpperCase()));
                temperatureLabel.setText(String.format("%.1f°C", temperature));
                descriptionLabel.setText(capitalizeFirst(description));
                humidityLabel.setText(String.format("💧 %.1f%%", humidity));
                pressureLabel.setText(String.format("🌡️ %.1f hPa", pressure));

                revalidate();
                repaint();
                System.out.println("WeatherPanel: Update completed successfully");
            } catch (Exception e) {
                System.err.println("Error updating weather panel: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private String capitalizeFirst(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}