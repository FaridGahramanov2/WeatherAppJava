package patterns.strategy;

public class MetricProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        return kelvin;
    }

    @Override
    public String getTemperatureUnit() {
        return "°C";
    }
}
