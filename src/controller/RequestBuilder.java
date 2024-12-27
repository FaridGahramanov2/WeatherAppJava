package controller;

import service.ApiConfig;
//Builder
public class RequestBuilder {
    private String endpoint;
    private String city;
    private String units = "metric";
    private String lang = "en";
    private String apiKey;


    public RequestBuilder setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }


    public RequestBuilder setCity(String city) {
        this.city = city;
        return this;
    }


    public RequestBuilder setUnits(String units) {
        this.units = units;
        return this;
    }


    public RequestBuilder setLanguage(String lang) {
        this.lang = lang;
        return this;
    }


    public RequestBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }


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