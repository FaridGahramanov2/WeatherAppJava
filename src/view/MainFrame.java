package view;

import controller.WeatherController;
import model.WeatherConditions;
import model.WeatherData;
import controller.RefreshWeatherCommand;
import controller.SaveSettingsCommand;
import controller.ImperialProcessor;
import controller.MetricProcessor;
import patterns.strategy.WeatherProcessingStrategy;
import view.components.GradientPanel;
import view.utility.ColorClimate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.time.Instant;

public class MainFrame extends JFrame {
    private final WeatherPanel weatherPanel;
    private final WeatherController controller;
    private JTextField cityInput;
    private JButton searchButton;
    private JButton refreshButton;
    private JButton saveButton;
    private GradientPanel backgroundPanel;
    private JComboBox<String> unitSelector;
    private JPanel searchPanel;

    // Colors
    private static final Color PRIMARY_COLOR = new Color(0x2196F3);
    private static final Color ACCENT_COLOR = new Color(0x64B5F6);
    private static final Color TEXT_COLOR = new Color(0xFFFFFF);
    private static final Color DROP_COLOR = new Color(0x000000);

    private static final Color INPUT_BG_COLOR = new Color(255, 255, 255, 20);

    // Style constants
    private static final int BUTTON_HEIGHT = 40;
    private static final int INPUT_HEIGHT = 40;
    private static final int PANEL_SPACING = 25;
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 32);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

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
        setTitle("Weather Forecast");
        setSize(1000, 700);
        setMinimumSize(new Dimension(1200, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        backgroundPanel = new GradientPanel(PRIMARY_COLOR, ACCENT_COLOR);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(PANEL_SPACING, PANEL_SPACING));
    }

    private void setupComponents() {
        // Search input field
        cityInput = new JTextField(25);
        cityInput.setPreferredSize(new Dimension(300, INPUT_HEIGHT));
        setupInputField(cityInput);

        // Unit selector
        unitSelector = new JComboBox<>(new String[]{"Metric", "Imperial"});
        unitSelector.setPreferredSize(new Dimension(140, INPUT_HEIGHT));
        setupComboBox(unitSelector);

        // Create buttons with icons
        ImageIcon searchIcon = new ImageIcon("assets/images/utils/search.png");
        ImageIcon refreshIcon = new ImageIcon("assets/images/utils/refresh.png");
        ImageIcon saveIcon = new ImageIcon("assets/images/utils/save.png");

        searchButton = createStyledButton("Search", searchIcon);
        refreshButton = createStyledButton("Refresh", refreshIcon);
        saveButton = createStyledButton("Save Settings", saveIcon);

        // Weather panel
        weatherPanel.setOpaque(false);
        weatherPanel.setBorder(new EmptyBorder(PANEL_SPACING, PANEL_SPACING * 2, PANEL_SPACING * 2, PANEL_SPACING * 2));
    }

    private void setupInputField(JTextField field) {
        field.setBackground(INPUT_BG_COLOR);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        field.setFont(INPUT_FONT);
        field.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    private void setupComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(INPUT_BG_COLOR);
        comboBox.setForeground(DROP_COLOR);
        comboBox.setFont(INPUT_FONT);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true));

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? new Color(255, 0, 0, 40) : INPUT_BG_COLOR);
                setForeground(TEXT_COLOR);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
    }

    private JButton createStyledButton(String text, ImageIcon icon) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(140, BUTTON_HEIGHT));
        button.setBackground(new Color(255, 255, 255, 30));
        button.setForeground(TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (icon != null) {
            // Scale the icon to fit nicely
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(newImg);
            button.setIcon(scaledIcon);
            button.setIconTextGap(8);
        }

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
        JPanel mainContent = new JPanel(new BorderLayout(0, PANEL_SPACING));
        mainContent.setOpaque(false);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(PANEL_SPACING, PANEL_SPACING, 0, PANEL_SPACING));

        // Title
        JLabel titleLabel = new JLabel("Weather Forecast", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(titleLabel);
        topPanel.add(Box.createVerticalStrut(20));

        // Search panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setOpaque(false);

        // Search input group
        JPanel searchInputGroup = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchInputGroup.setOpaque(false);
        searchInputGroup.add(cityInput);
        searchInputGroup.add(unitSelector);

        // Button group
        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonGroup.setOpaque(false);
        buttonGroup.add(searchButton);
        buttonGroup.add(refreshButton);
        buttonGroup.add(saveButton);

        searchPanel.add(searchInputGroup);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(buttonGroup);

        topPanel.add(searchPanel);

        mainContent.add(topPanel, BorderLayout.NORTH);
        mainContent.add(weatherPanel, BorderLayout.CENTER);

        backgroundPanel.add(mainContent);
    }

    private void setupEventListeners() {
        searchButton.addActionListener(e -> performSearch());
        refreshButton.addActionListener(e -> performRefresh());
        saveButton.addActionListener(e -> performSave());

        cityInput.addActionListener(e -> performSearch());
        getRootPane().setDefaultButton(searchButton);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                handleResize();
            }
        });
    }

    private void handleResize() {
        int width = getWidth();
        if (width < 1000) {
            searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        } else {
            searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        }
        revalidate();
    }

    private void performSearch() {
        String city = cityInput.getText().trim();
        if (!city.isEmpty()) {
            SwingUtilities.invokeLater(() -> {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                searchButton.setEnabled(false);
                searchButton.setText("Searching...");

                try {
                    controller.fetchWeatherData(city);
                    WeatherData weatherData = controller.getWeatherData();
                    if (weatherData != null) {
                        updateBackground(weatherData);
                    } else {
                        showError("City not found! Please check the spelling and try again.");
                    }
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                    searchButton.setEnabled(true);
                    searchButton.setText("Search");
                }
            });
        } else {
            showError("Please enter a city name.");
        }
    }

    private void performRefresh() {
        if (controller.getWeatherData() != null) {
            String city = cityInput.getText().trim();
            if (!city.isEmpty()) {
                SwingUtilities.invokeLater(() -> {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    refreshButton.setEnabled(false);
                    refreshButton.setText("Refreshing...");

                    try {
                        RefreshWeatherCommand refreshCommand = new RefreshWeatherCommand(controller, city);
                        controller.executeCommand(refreshCommand);

                        WeatherData weatherData = controller.getWeatherData();
                        if (weatherData != null) {
                            updateBackground(weatherData);
                        } else {
                            showError("Failed to refresh weather data. Please try again.");
                        }
                    } finally {
                        setCursor(Cursor.getDefaultCursor());
                        refreshButton.setEnabled(true);
                        refreshButton.setText("Refresh");
                    }
                });
            } else {
                showError("Please enter a city name.");
            }
        } else {
            showError("No data to refresh. Please search for a city first.");
        }
    }

    private void performSave() {
        try {
            String selectedUnit = (String) unitSelector.getSelectedItem();
            WeatherProcessingStrategy strategy = "Imperial".equals(selectedUnit)
                    ? new ImperialProcessor()
                    : new MetricProcessor();

            SaveSettingsCommand saveCommand = new SaveSettingsCommand(controller, strategy);
            controller.executeCommand(saveCommand);

            if (controller.getWeatherData() != null) {
                weatherPanel.setStrategy(strategy);
                performRefresh();
            }

            JOptionPane optionPane = new JOptionPane(
                    "Settings saved successfully!",
                    JOptionPane.INFORMATION_MESSAGE
            );
            JDialog dialog = optionPane.createDialog(this, "Success");
            dialog.setBackground(new Color(255, 255, 255, 240));
            dialog.setVisible(true);

        } catch (Exception e) {
            showError("Failed to save settings: " + e.getMessage());
        }
    }

    private void showError(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane optionPane = new JOptionPane(
                    message,
                    JOptionPane.ERROR_MESSAGE
            );
            JDialog dialog = optionPane.createDialog(this, "Error");
            dialog.setBackground(new Color(255, 255, 255, 240));
            dialog.setVisible(true);
        });
    }

    private void updateBackground(WeatherData weatherData) {
        if (weatherData == null) return;

        SwingUtilities.invokeLater(() -> {
            try {
                long currentTime = Instant.now().getEpochSecond();

                ColorClimate colorClimate = new ColorClimate(
                        WeatherConditions.fromString(weatherData.getDescription()),
                        weatherData.getTemperature(),
                        currentTime,
                        weatherData.getSunrise(),
                        weatherData.getSunset()
                );

                Color[] colors = colorClimate.getColorPalette();

                GradientPanel newBackgroundPanel = new GradientPanel(colors[0], colors[1]);
                newBackgroundPanel.setLayout(new BorderLayout(PANEL_SPACING, PANEL_SPACING));

                Component mainContent = null;
                for (Component component : backgroundPanel.getComponents()) {
                    if (component instanceof JPanel) {
                        mainContent = component;
                        break;
                    }
                }

                if (mainContent != null) {
                    newBackgroundPanel.add(mainContent, BorderLayout.CENTER);
                }

                backgroundPanel = newBackgroundPanel;
                setContentPane(backgroundPanel);

                revalidate();
                repaint();

            } catch (Exception e) {
                System.err.println("Error updating background: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}