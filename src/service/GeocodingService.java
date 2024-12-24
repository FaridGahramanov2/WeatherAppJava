package service;

import patterns.singleton.ApiClient;

public class GeocodingService {
    private final ApiClient apiClient;

    public GeocodingService() {
        this.apiClient = ApiClient.getInstance();
    }

    public String validateCity(String cityName) throws Exception {
        // Basic validation for now - can be expanded to use geocoding API
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }
        return cityName.trim();
    }
}