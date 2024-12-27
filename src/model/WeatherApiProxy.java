package model;

import service.ApiClient;

import java.util.HashMap;
import java.util.Map;

//Proxy
public class WeatherApiProxy {
    private final ApiClient apiClient;
    private final Map<String, String> cache;

    public WeatherApiProxy() {
        this.apiClient = ApiClient.getInstance();
        this.cache = new HashMap<>();
    }

    public String getWeatherData(String city, String url) throws Exception {

        if (cache.containsKey(city)) {
            System.out.println("Cache hit for city: " + city);
            return cache.get(city);
        }


        String response = apiClient.fetchWeatherData(url);
        System.out.println("Cache miss. Fetched data for city: " + city);


        cache.put(city, response);

        return response;
    }
}