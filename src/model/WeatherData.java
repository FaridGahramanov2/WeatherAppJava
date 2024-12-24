package model;

import patterns.observer.WeatherObserver;
import patterns.observer.WeatherSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds weather data and notifies observers when updated.
 */
public class WeatherData implements WeatherSubject {
    private final List<WeatherObserver> observers;
    private double temperature;
    private double humidity;
    private double pressure;
    private String description;
    private String countryCode; // New field to store the country code

    private long sunrise; // Added for day/night determination
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

    /**
     * Updates the weather data and notifies observers.
     *
     * @param temperature  The temperature.
     * @param humidity     The humidity.
     * @param pressure     The pressure.
     * @param description  The weather description.
     * @param countryCode  The country code for the location.
     */
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




    // Getters


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
