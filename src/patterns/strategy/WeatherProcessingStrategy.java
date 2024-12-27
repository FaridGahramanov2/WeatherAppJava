package patterns.strategy;

//Strategy
public interface WeatherProcessingStrategy {
    double convertTemperature(double value);
    String getTemperatureUnit();
}

