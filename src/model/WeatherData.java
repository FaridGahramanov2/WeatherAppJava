package model;

import patterns.observer.WeatherObserver;
import patterns.observer.WeatherSubject;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements WeatherSubject {
    private List<WeatherObserver> observers;
    private double temperature;
    private double humidity;
    private double pressure;
    private String description;

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

    public void updateData(double temperature, double humidity, double pressure, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notifying " + observers.size() + " observers");
        for (WeatherObserver observer : observers) {
            observer.update(temperature, humidity, pressure, description);
        }
    }
}