package model;

public class DailyForecast {
    private double maxTemp;
    private double minTemp;
    private int humidity;
    private String description;
    private String icon;
    private long date;

    public DailyForecast(double maxTemp, double minTemp, int humidity,
                         String description, String icon, long date) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.description = description;
        this.icon = icon;
        this.date = date;
    }

    // Getters and setters
    public double getMaxTemp() { return maxTemp; }
    public void setMaxTemp(double maxTemp) { this.maxTemp = maxTemp; }
    public double getMinTemp() { return minTemp; }
    public void setMinTemp(double minTemp) { this.minTemp = minTemp; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }
}