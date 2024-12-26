package patterns.proxy;

import patterns.singleton.ApiClient;

import java.util.HashMap;
import java.util.Map;

public class WeatherApiProxy {
    private final ApiClient apiClient;
    private final Map<String, String> cache; // In-memory cache

    public WeatherApiProxy() {
        this.apiClient = ApiClient.getInstance();
        this.cache = new HashMap<>();
    }

    public String getWeatherData(String city, String url) throws Exception {
        // Check cache
        if (cache.containsKey(city)) {
            System.out.println("Cache hit for city: " + city);
            return cache.get(city);
        }

        // Fetch data from API
        String response = apiClient.fetchWeatherData(url);
        System.out.println("Cache miss. Fetched data for city: " + city);

        // Store in cache
        cache.put(city, response);

        return response;
    }
}