package patterns.facade;

import model.WeatherData;
import org.json.JSONObject;
import patterns.singleton.ApiClient;
import patterns.strategy.WeatherProcessingStrategy;
import patterns.adapter.ApiResponseAdapter;

public class WeatherServiceFacade {
    private final ApiClient apiClient;
    private final ApiResponseAdapter adapter;
    private WeatherProcessingStrategy processingStrategy;

    public WeatherServiceFacade(WeatherProcessingStrategy strategy) {
        this.apiClient = ApiClient.getInstance();
        this.adapter = new ApiResponseAdapter();
        this.processingStrategy = strategy;
    }

    public WeatherData getWeatherData(String city) {
        try {
            System.out.println("Fetching weather data for: " + city);
            String response = apiClient.fetchWeatherData(city);
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