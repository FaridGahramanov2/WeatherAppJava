package view;

import controller.WeatherController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final WeatherPanel weatherPanel;
    private final WeatherController controller;
    private final JTextField cityInput;
    private final JButton searchButton;

    public MainFrame() {
        controller = new WeatherController();
        weatherPanel = new WeatherPanel();
        controller.addObserver(weatherPanel);

        setTitle("Weather App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        cityInput = new JTextField(20);
        searchButton = new JButton("Search");

        topPanel.add(cityInput);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);
        add(weatherPanel, BorderLayout.CENTER);

        setupEventListeners();
    }



    private void setupEventListeners() {
        searchButton.addActionListener(e -> {
            String city = cityInput.getText();
            controller.fetchWeatherData(city);
        });
    }
}