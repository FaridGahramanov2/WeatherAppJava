package view;

import controller.WeatherController;
import model.WeatherConditions;
import model.WeatherData;
import view.components.GradientPanel;
import view.utility.ColorClimate;
import view.utility.WeatherStateResolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.Instant;

public class MainFrame extends JFrame {
    private final WeatherPanel weatherPanel;
    private final WeatherController controller;
    private JTextField cityInput;
    private JButton searchButton;
    private GradientPanel backgroundPanel;

    // Custom colors
    private static final Color PRIMARY_COLOR = new Color(0x2196F3);
    private static final Color ACCENT_COLOR = new Color(0x64B5F6);
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color INPUT_BG_COLOR = new Color(255, 255, 255, 20);

    public MainFrame() {
        controller = new WeatherController();
        weatherPanel = new WeatherPanel();
        controller.addObserver(weatherPanel);

        initializeFrame();
        setupComponents();
        setupLayout();
        setupEventListeners();
    }

    private void initializeFrame() {
        setTitle("Weather App");
        setSize(900, 600);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main gradient background
        backgroundPanel = new GradientPanel(PRIMARY_COLOR, ACCENT_COLOR);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(20, 20));
    }

    private void setupComponents() {
        // Create and style the search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        searchPanel.setOpaque(false);

        // Style the city input
        cityInput = new JTextField(20);
        cityInput.setPreferredSize(new Dimension(250, 35));
        cityInput.setBackground(INPUT_BG_COLOR);
        cityInput.setForeground(TEXT_COLOR);
        cityInput.setCaretColor(TEXT_COLOR);
        cityInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cityInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Style the search button
        searchButton = createStyledButton("Search");

        searchPanel.add(cityInput);
        searchPanel.add(searchButton);

        // Style the weather panel
        weatherPanel.setOpaque(false);
        weatherPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 35));
        button.setBackground(new Color(255, 255, 255, 30));
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255, 30));
            }
        });

        return button;
    }

    private void setupLayout() {
        // Create main content panel with BorderLayout
        JPanel mainContent = new JPanel(new BorderLayout(0, 20));
        mainContent.setOpaque(false);

        // Create and setup top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Add app title
        JLabel titleLabel = new JLabel("Weather Forecast", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_COLOR);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Add search panel
        JPanel searchWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchWrapper.setOpaque(false);
        searchWrapper.add(cityInput);
        searchWrapper.add(searchButton);
        topPanel.add(searchWrapper, BorderLayout.CENTER);

        // Add panels to main content
        mainContent.add(topPanel, BorderLayout.NORTH);
        mainContent.add(weatherPanel, BorderLayout.CENTER);

        // Add main content to background panel
        backgroundPanel.add(mainContent);

        // Set preferred size for weatherPanel
        weatherPanel.setPreferredSize(new Dimension(600, 400));
    }

    private void setupEventListeners() {
        searchButton.addActionListener(e -> performSearch());
        cityInput.addActionListener(e -> performSearch());
    }

    private void performSearch() {
        String city = cityInput.getText().trim();
        if (!city.isEmpty()) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            controller.fetchWeatherData(city);

            WeatherData weatherData = controller.getWeatherData();
            if (weatherData != null) {
                updateBackground(weatherData);
            } else {
                showError("City not found! Please check the spelling and try again.");
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void updateBackground(WeatherData weatherData) {
        // Get current time and compare with sunrise/sunset
        long currentTime = Instant.now().getEpochSecond();

        ColorClimate colorClimate = new ColorClimate(
                WeatherConditions.fromString(weatherData.getDescription()),
                weatherData.getTemperature(),
                currentTime,
                weatherData.getSunrise(),
                weatherData.getSunset()
        );
        Color[] colors = colorClimate.getColorPalette();

        // Create new background panel
        GradientPanel newBackgroundPanel = new GradientPanel(colors[0], colors[1]);
        newBackgroundPanel.setLayout(new BorderLayout(20, 20));

        // Save the main content panel
        Component mainContent = null;
        for (Component component : backgroundPanel.getComponents()) {
            if (component instanceof JPanel) {
                mainContent = component;
                break;
            }
        }

        // Add the main content back to the new panel
        if (mainContent != null) {
            newBackgroundPanel.add(mainContent, BorderLayout.CENTER);
        }

        // Update the background panel reference
        backgroundPanel = newBackgroundPanel;
        setContentPane(backgroundPanel);

        revalidate();
        repaint();
    }
}