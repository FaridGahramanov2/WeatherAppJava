package patterns.observer;

//Observer
public interface WeatherObserver {
    void update(double temperature, double humidity, double pressure, String description, String countryCode);
}