package service.api;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class OpenWeatherMapApi {
    private final HttpClient httpClient;

    public OpenWeatherMapApi() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getCurrentWeather(String city) throws IOException, InterruptedException {
        String url = ApiUtils.buildUrl("weather", "q=" + city);
        return sendRequest(url);
    }

    public String getForecast(String city) throws IOException, InterruptedException {
        String url = ApiUtils.buildUrl("forecast", "q=" + city);
        return sendRequest(url);
    }

    private String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}