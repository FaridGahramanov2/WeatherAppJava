package patterns.observer;

public interface WeatherObserver {
    void update(double temperature, double humidity, double pressure, String description, String countryCode);
}