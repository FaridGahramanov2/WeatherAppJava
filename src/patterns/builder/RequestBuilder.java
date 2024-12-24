package patterns.builder;

public class RequestBuilder {
    private String endpoint;
    private String city;
    private String units = "metric";
    private String language = "en";

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

    public RequestBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String build() {
        return String.format("%s?q=%s&units=%s&lang=%s",
                endpoint, city, units, language);
    }
}