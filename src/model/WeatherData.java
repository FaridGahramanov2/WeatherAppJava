package model;

import patterns.observer.WeatherObserver;
import patterns.observer.WeatherSubject;

import java.util.ArrayList;
import java.util.List;


public class WeatherData implements WeatherSubject {
    private final List<WeatherObserver> observers;
    private double temperature;
    private double humidity;
    private double pressure;
    private String description;
    private String countryCode;

    private long sunrise;
    private long sunset;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        System.out.println("Registering observer: " + observer);
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void updateData(double temperature, double humidity, double pressure, String description, String countryCode, long sunrise, long sunset) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.countryCode = countryCode;
        this.sunrise = sunrise;
        this.sunset = sunset;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notifying " + observers.size() + " observers");
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity, pressure, description, countryCode);
        }
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getDescription() {
        return description;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
