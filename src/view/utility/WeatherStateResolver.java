package view.utility;

import model.WeatherConditions;

public class WeatherStateResolver {
    private static final String ICON_BASE_PATH = "assets/images/weather/";

    public static String getWeatherImagePath(String condition) {
        if (condition == null || condition.isEmpty()) {
            return ICON_BASE_PATH + "default.png";
        }

        try {
            WeatherConditions weatherCondition = WeatherConditions.fromString(condition);
            switch (weatherCondition) {
                case CLEAR:
                    return ICON_BASE_PATH + "sunny.png";
                case RAIN:
                    return ICON_BASE_PATH + "rainy.png";
                case SNOW:
                    return ICON_BASE_PATH + "snowy.png";
                case CLOUDS:
                    return ICON_BASE_PATH + "cloudy.png";
                case THUNDERSTORM:
                    return ICON_BASE_PATH + "dark-and-stormy.png";
                case DRIZZLE:
                    return ICON_BASE_PATH + "drizzle.png";
                case FOG:
                    return ICON_BASE_PATH + "foggy.png";
                default:
                    return ICON_BASE_PATH + "default.png";
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return ICON_BASE_PATH + "default.png";
        }
    }
}
