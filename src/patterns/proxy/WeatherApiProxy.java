// patterns/proxy/WeatherApiProxy.java
package patterns.proxy;

import patterns.singleton.ApiClient;
import patterns.singleton.WeatherDataCache;

public class WeatherApiProxy {
    private final ApiClient apiClient;
    private final WeatherDataCache cache;

    public WeatherApiProxy() {
        this.apiClient = ApiClient.getInstance();
        this.cache = WeatherDataCache.getInstance();
    }

    public String getWeatherData(String city) throws Exception {
        String cachedData = cache.get(city);
        if (cachedData != null) {
            return cachedData;
        }

        String freshData = apiClient.fetchWeatherData(city);
        cache.put(city, freshData);
        return freshData;
    }
}