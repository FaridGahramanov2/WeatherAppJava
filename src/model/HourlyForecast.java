package model;

public class HourlyForecast {
    private double temperature;
    private int humidity;
    private String description;
    private String icon;
    private long time;

    public HourlyForecast(double temperature, int humidity,
                          String description, String icon, long time) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.icon = icon;
        this.time = time;
    }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public long getTime() { return time; }
    public void setTime(long time) { this.time = time; }
}