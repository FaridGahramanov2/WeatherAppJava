package controller;

import model.WeatherData;
import patterns.facade.WeatherServiceFacade;
import patterns.observer.WeatherObserver;
import patterns.strategy.MetricProcessor;
import patterns.strategy.WeatherProcessingStrategy;

import java.util.ArrayList;
import java.util.List;

// WeatherController.java
public class WeatherController implements WeatherObserver {
    private final WeatherServiceFacade weatherService;
    private WeatherData weatherData;
    private List<WeatherObserver> observers;
    private WeatherProcessingStrategy processingStrategy;
    private String lastCity;

    public WeatherController() {
        this.observers = new ArrayList<>();
        this.processingStrategy = new MetricProcessor();
        this.weatherService = new WeatherServiceFacade(processingStrategy);
        this.weatherData = new WeatherData();
    }





    public void fetchWeatherData(String city) {
        try {
            this.lastCity = city;
            WeatherData newData = weatherService.getWeatherData(city);
            // Transfer observers to new data
            for (WeatherObserver observer : observers) {
                newData.registerObserver(observer);
            }
            this.weatherData = newData;
            this.weatherData.notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
        weatherData.registerObserver(observer);
    }

    public void setProcessingStrategy(WeatherProcessingStrategy strategy) {
        this.processingStrategy = strategy;
        weatherService.setProcessingStrategy(strategy);
        // Refresh weather data with new strategy
        if (lastCity != null) {
            fetchWeatherData(lastCity);
        }
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description) {
        // Forward update to observers if needed
        weatherData.notifyObservers();
    }





}


