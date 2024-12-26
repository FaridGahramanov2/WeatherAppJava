package service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

public class ApiClient {
    private static ApiClient instance;
    private final HttpClient httpClient;

    private ApiClient() {
        this.httpClient = HttpClient.newHttpClient();
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

    public String fetchWeatherData(String url) throws IOException, InterruptedException {
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