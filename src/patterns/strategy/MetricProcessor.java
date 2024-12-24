package patterns.strategy;

public class MetricProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        return kelvin; // The API is already returning Celsius since we use units=metric
    }

    @Override
    public String getTemperatureUnit() {
        return "°C";
    }
}
