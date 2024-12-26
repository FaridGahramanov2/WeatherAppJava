package patterns.facade;

import model.WeatherData;
import patterns.builder.RequestBuilder;
import patterns.proxy.WeatherApiProxy;
import patterns.strategy.WeatherProcessingStrategy;
import patterns.adapter.ApiResponseAdapter;

public class WeatherServiceFacade {
    private final WeatherApiProxy proxy;
    private final ApiResponseAdapter adapter;
    private WeatherProcessingStrategy processingStrategy;

    public WeatherServiceFacade(WeatherProcessingStrategy strategy) {
        this.proxy = new WeatherApiProxy(); // Use the proxy
        this.adapter = new ApiResponseAdapter();
        this.processingStrategy = strategy;
    }

    public WeatherData getWeatherData(String city) {
        try {
            System.out.println("Fetching weather data for: " + city);

            // Use RequestBuilder to construct the API URL
            String url = new RequestBuilder()
                    .setEndpoint("weather")
                    .setCity(city)
                    .setUnits(processingStrategy.getTemperatureUnit().equals("°F") ? "imperial" : "metric")
                    .setLanguage("en")
                    .build();

            System.out.println("Request URL: " + url);

            // Fetch data via the proxy
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