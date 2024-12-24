package patterns.strategy;

public interface WeatherProcessingStrategy {
    double convertTemperature(double value);
    String getTemperatureUnit();
}

