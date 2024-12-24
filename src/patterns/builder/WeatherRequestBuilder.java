package patterns.builder;

public class WeatherRequestBuilder {
    private String city;
    private String units = "metric";
    private String lang = "en";
    private String apiKey;

    public WeatherRequestBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public WeatherRequestBuilder setUnits(String units) {
        this.units = units;
        return this;
    }

    public WeatherRequestBuilder setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public WeatherRequestBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String build() {
        return String.format("weather?q=%s&units=%s&lang=%s&appid=%s",
                city, units, lang, apiKey);
    }
}