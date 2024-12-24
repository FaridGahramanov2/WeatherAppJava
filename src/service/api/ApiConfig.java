package service.api;

public class ApiConfig {
    private static final String API_KEY = "02571d32cb396b192ca04d40330c0e93";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}