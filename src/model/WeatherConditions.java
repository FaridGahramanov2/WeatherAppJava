package model;

public enum WeatherConditions {
    CLEAR("clear", "clear sky"),
    RAIN("rain", "light rain", "moderate rain", "heavy intensity rain"),
    SNOW("snow", "light snow", "heavy snow"),
    CLOUDS("clouds", "overcast clouds", "broken clouds", "few clouds", "scattered clouds"),
    THUNDERSTORM("thunderstorm"),
    DRIZZLE("drizzle", "light drizzle"),
    FOG("fog", "haze", "mist", "smoke", "dust");

    private final String[] descriptions;

    WeatherConditions(String... descriptions) {
        this.descriptions = descriptions;
    }

    public static WeatherConditions fromString(String condition) {
        for (WeatherConditions wc : values()) {
            for (String desc : wc.descriptions) {
                if (desc.equalsIgnoreCase(condition)) {
                    return wc;
                }
            }
        }
        throw new IllegalArgumentException("Unknown weather condition: " + condition);
    }
}
