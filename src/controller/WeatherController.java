package controller;

import model.WeatherData;
import patterns.facade.WeatherServiceFacade;
import patterns.observer.WeatherObserver;
import patterns.strategy.MetricProcessor;
import patterns.strategy.WeatherProcessingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the weather data fetching and observer updates.
 */
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

    /**
     * Fetches weather data for the given city and updates the WeatherData object.
     *
     * @param city The city for which weather data is to be fetched.
     */
    public void fetchWeatherData(String city) {
        try {
            this.lastCity = city;
            WeatherData newData = weatherService.getWeatherData(city);

            if (newData != null) {
                // Transfer all observers to the new WeatherData instance
                for (WeatherObserver observer : observers) {
                    newData.registerObserver(observer);
                }

                // Update the current WeatherData instance and notify observers
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

    /**
     * Adds a new observer to the weather data.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(WeatherObserver observer) {
        observers.add(observer);
        weatherData.registerObserver(observer);
    }

    /**
     * Sets a new processing strategy and fetches updated weather data.
     *
     * @param strategy The new processing strategy.
     */
    public void setProcessingStrategy(WeatherProcessingStrategy strategy) {
        this.processingStrategy = strategy;
        weatherService.setProcessingStrategy(strategy);

        if (lastCity != null) {
            fetchWeatherData(lastCity);
        }
    }

    /**
     * Returns the current WeatherData object.
     *
     * @return The current WeatherData object.
     */
    public WeatherData getWeatherData() {
        return weatherData;
    }

    @Override
    public void update(double temperature, double humidity, double pressure, String description, String countryCode) {
        weatherData.notifyObservers();
    }
}
