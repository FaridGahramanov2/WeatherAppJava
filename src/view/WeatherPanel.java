package view;

import model.WeatherData;
import patterns.observer.WeatherObserver;
import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel implements WeatherObserver {
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel pressureLabel;
    private JLabel descriptionLabel;


    public WeatherPanel() {
        setLayout(new GridLayout(4, 1));
        initComponents();
    }

    private void initComponents() {
        temperatureLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        pressureLabel = new JLabel("Pressure: ");
        descriptionLabel = new JLabel("Description: ");

        add(temperatureLabel);
        add(humidityLabel);
        add(pressureLabel);
        add(descriptionLabel);
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description) {
        SwingUtilities.invokeLater(() -> {
            temperatureLabel.setText(String.format("Temperature: %.1f°C", temperature));
            humidityLabel.setText(String.format("Humidity: %.1f%%", humidity));
            pressureLabel.setText(String.format("Pressure: %.1f hPa", pressure));
            descriptionLabel.setText("Description: " + description);
            revalidate();
            repaint();
        });
    }
}