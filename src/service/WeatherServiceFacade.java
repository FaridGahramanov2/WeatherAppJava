package service;

import model.WeatherData;
import controller.RequestBuilder;
import model.WeatherApiProxy;
import patterns.strategy.WeatherProcessingStrategy;
import model.ApiResponseAdapter;

public class WeatherServiceFacade {
    private final WeatherApiProxy proxy;
    private final ApiResponseAdapter adapter;
    private WeatherProcessingStrategy processingStrategy;

    public WeatherServiceFacade(WeatherProcessingStrategy strategy) {
        this.proxy = new WeatherApiProxy();
        this.adapter = new ApiResponseAdapter();
        this.processingStrategy = strategy;
    }

    public WeatherData getWeatherData(String city) {
        try {
            System.out.println("Fetching weather data for: " + city);

            // Always use standard (Kelvin) units from API
            String url = new RequestBuilder()
                    .setEndpoint("weather")
                    .setCity(city)
                    .setUnits("standard")  // Always fetch in Kelvin
                    .setLanguage("en")
                    .build();

            System.out.println("Request URL: " + url);

            String response = proxy.getWeatherData(city, url);
            System.out.println("Received response: " + response);

            if (response != null && !response.isEmpty()) {
                WeatherData data = adapter.convertToWeatherData(response, processingStrategy);
                System.out.println("Converted weather data: " + data);
                return data;
            } else {
                throw new RuntimeException("Empty response received from API");
            }
        } catch (Exception e) {
            System.err.println("Error in WeatherServiceFacade: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
        }
    }

    public void setProcessingStrategy(WeatherProcessingStrategy strategy) {
        this.processingStrategy = strategy;
    }
}