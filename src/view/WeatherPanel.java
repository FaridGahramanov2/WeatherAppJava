package view;

import patterns.observer.WeatherObserver;
import patterns.strategy.WeatherProcessingStrategy;
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
    private JPanel detailsPanel;
    private WeatherProcessingStrategy currentStrategy;

    // Enhanced styling
    private static final Color PRIMARY_TEXT = new Color(255, 255, 255);
    private static final Color SECONDARY_TEXT = new Color(255, 255, 255, 200);
    private static final Font TEMP_FONT = new Font("Segoe UI", Font.BOLD, 72);
    private static final Font LOCATION_FONT = new Font("Segoe UI", Font.BOLD, 32);
    private static final Font DESC_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font DETAILS_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font DETAILS_LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public WeatherPanel() {
        setLayout(new GridBagLayout());
        initComponents();
    }

    public void setStrategy(WeatherProcessingStrategy strategy) {
        this.currentStrategy = strategy;
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Initialize main weather icon with larger size
        weatherIconLabel = new JLabel("", JLabel.CENTER);
        weatherIconLabel.setPreferredSize(new Dimension(200, 200));

        // Enhanced location display with flag
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        locationPanel.setOpaque(false);

        locationLabel = createStyledLabel("", LOCATION_FONT, PRIMARY_TEXT);
        flagLabel = new JLabel("", JLabel.CENTER);
        locationPanel.add(locationLabel);
        locationPanel.add(flagLabel);

        // Enhanced temperature display
        temperatureLabel = createStyledLabel("", TEMP_FONT, PRIMARY_TEXT);

        // Enhanced description
        descriptionLabel = createStyledLabel("", DESC_FONT, PRIMARY_TEXT);

        // Enhanced details panel
        detailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1, true),
                new EmptyBorder(20, 30, 20, 30)
        ));

        // Create styled detail containers
        JPanel humidityContainer = createDetailContainer("💧", "Humidity");
        JPanel pressureContainer = createDetailContainer("🌡️", "Pressure");

        humidityLabel = createStyledLabel("", DETAILS_FONT, SECONDARY_TEXT);
        pressureLabel = createStyledLabel("", DETAILS_FONT, SECONDARY_TEXT);

        humidityContainer.add(humidityLabel);
        pressureContainer.add(pressureLabel);

        detailsPanel.add(humidityContainer);
        detailsPanel.add(Box.createHorizontalStrut(30)); // Spacing between details
        detailsPanel.add(pressureContainer);

        // Layout components with proper spacing
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 30, 10);
        add(weatherIconLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 15, 10);
        add(locationPanel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(15, 10, 15, 10);
        add(temperatureLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 30, 10);
        add(descriptionLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(detailsPanel, gbc);

        setBorder(new EmptyBorder(30, 30, 30, 30));
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JPanel createDetailContainer(String icon, String labelText) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        // Create icon and label
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        iconLabel.setForeground(SECONDARY_TEXT);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(DETAILS_LABEL_FONT);
        textLabel.setForeground(SECONDARY_TEXT);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components with spacing
        container.add(iconLabel);
        container.add(Box.createVerticalStrut(5));
        container.add(textLabel);
        container.add(Box.createVerticalStrut(10));

        return container;
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description, String countryCode) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Update weather icon with animation effect
                String iconPath = WeatherStateResolver.getWeatherImagePath(description);
                ImageLabelGenerator weatherIconGenerator = new ImageLabelGenerator(iconPath, new Rectangle(0, 0, 200, 200));
                weatherIconLabel.setIcon(weatherIconGenerator.createImageLabel().getIcon());

                // Update flag with smooth scaling
                String flagPath = CountryImageResolver.getCountryImagePath(countryCode);
                ImageIcon flagIcon = new ImageIcon(flagPath);
                Image scaledFlag = flagIcon.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
                flagLabel.setIcon(new ImageIcon(scaledFlag));

                // Update text components with fade effect
                locationLabel.setText(countryCode.toUpperCase());
                String unit = currentStrategy != null ? currentStrategy.getTemperatureUnit() : "°C";
                temperatureLabel.setText(String.format("%.1f%s", temperature, unit));
                descriptionLabel.setText(capitalizeFirst(description));
                humidityLabel.setText(String.format("%.1f%%", humidity));
                pressureLabel.setText(String.format("%.1f hPa", pressure));

                // Trigger revalidation and repaint
                revalidate();
                repaint();
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