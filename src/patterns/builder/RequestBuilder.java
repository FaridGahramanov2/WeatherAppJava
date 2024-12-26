package patterns.builder;

import service.api.ApiConfig;

public class RequestBuilder {
    private String endpoint; // API endpoint (e.g., weather, forecast)
    private String city;     // City name
    private String units = "metric"; // Default units: metric
    private String lang = "en";      // Default language: English
    private String apiKey;           // API Key (optional, defaults to ApiConfig)

    // Set the API endpoint (e.g., weather, forecast)
    public RequestBuilder setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    // Set the city name
    public RequestBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    // Set the units (metric, imperial, etc.)
    public RequestBuilder setUnits(String units) {
        this.units = units;
        return this;
    }

    // Set the language for the API response
    public RequestBuilder setLanguage(String lang) {
        this.lang = lang;
        return this;
    }

    // Set the API key (optional)
    public RequestBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    // Build the final URL
    public String build() {
        if (endpoint == null || city == null) {
            throw new IllegalStateException("Endpoint and city must be set before building the URL.");
        }

        return String.format(
                "%s%s?q=%s&units=%s&lang=%s&appid=%s",
                ApiConfig.getBaseUrl(),
                endpoint,
                city,
                units,
                lang,
                apiKey != null ? apiKey : ApiConfig.getApiKey()
        );
    }
}