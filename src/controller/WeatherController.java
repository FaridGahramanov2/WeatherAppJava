package controller;

import model.WeatherData;
import patterns.observer.WeatherObserver;
import patterns.strategy.WeatherProcessingStrategy;
import service.WeatherServiceFacade;
import view.WeatherPanel;

import java.util.ArrayList;
import java.util.List;

import patterns.command.Command;

public class WeatherController implements WeatherObserver {
    private final WeatherServiceFacade weatherService;
    private WeatherData weatherData;
    private final List<WeatherObserver> observers;
    private WeatherProcessingStrategy processingStrategy;
    private String lastCity;

    public WeatherController() {
        this.observers = new ArrayList<>();
        this.processingStrategy = new MetricProcessor();
        this.weatherService = new WeatherServiceFacade(processingStrategy);
        this.weatherData = new WeatherData();
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public void fetchWeatherData(String city) {
        try {
            this.lastCity = city;
            WeatherData newData = weatherService.getWeatherData(city);

            if (newData != null) {
                for (WeatherObserver observer : observers) {
                    newData.registerObserver(observer);
                }

                this.weatherData.updateData(
                        newData.getTemperature(),
                        newData.getHumidity(),
                        newData.getPressure(),
                        newData.getDescription(),
                        newData.getCountryCode(),
                        newData.getSunrise(),
                        newData.getSunset()
                );
            } else {
                System.err.println("Failed to fetch weather data for: " + city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProcessingStrategy(WeatherProcessingStrategy strategy) {
        this.processingStrategy = strategy;
        weatherService.setProcessingStrategy(strategy);

        // Update the panel's strategy if it's one of your observers
        for (WeatherObserver observer : observers) {
            if (observer instanceof WeatherPanel) {
                ((WeatherPanel) observer).setStrategy(strategy);
            }
        }

        // Re-fetch weather data using the updated strategy if a city is already selected
        if (lastCity != null) {
            fetchWeatherData(lastCity);
        }
    }

    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
        weatherData.registerObserver(observer);
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description, String countryCode) {
        weatherData.notifyObservers();
    }
}