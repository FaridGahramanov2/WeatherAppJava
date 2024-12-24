package service;

import model.WeatherData;
import patterns.singleton.ApiClient;
import patterns.adapter.ApiResponseAdapter;
import patterns.strategy.WeatherProcessingStrategy;

public class WeatherService {
    private final ApiClient apiClient;
    private final ApiResponseAdapter adapter;
    private final GeocodingService geocodingService;

    public WeatherService() {
        this.apiClient = ApiClient.getInstance();
        this.adapter = new ApiResponseAdapter();
        this.geocodingService = new GeocodingService();
    }

    public WeatherData getWeatherData(String city, WeatherProcessingStrategy strategy) throws Exception {
        String validCity = geocodingService.validateCity(city);
        String response = apiClient.fetchWeatherData(validCity);
        return adapter.convertToWeatherData(response, strategy);
    }
}