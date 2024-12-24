package model;

public class CurrentWeather {
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double pressure;
    private String description;
    private String icon;
    private long timestamp;

    public CurrentWeather(double temperature, double feelsLike, int humidity,
                          double pressure, String description, String icon) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.icon = icon;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and setters
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public long getTimestamp() { return timestamp; }
}