package patterns.singleton;

import service.api.ApiConfig;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private static ApiClient instance;
    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    private ApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.apiKey = ApiConfig.getApiKey();
        this.baseUrl = ApiConfig.getBaseUrl();
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }

    public String fetchWeatherData(String city) throws IOException, InterruptedException {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = baseUrl + "weather?q=" + encodedCity + "&appid=" + apiKey + "&units=metric";

        System.out.println("Requesting URL: " + url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status: " + response.statusCode());

        if (response.statusCode() != 200) {
            throw new IOException("API error: " + response.body());
        }

        return response.body();
    }
}