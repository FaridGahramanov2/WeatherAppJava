package patterns.adapter;

import model.WeatherData;
import org.json.JSONObject;
import patterns.strategy.WeatherProcessingStrategy;


public class ApiResponseAdapter {
    public WeatherData convertToWeatherData(String jsonString, WeatherProcessingStrategy strategy) {
        JSONObject jsonResponse = new JSONObject(jsonString);
        WeatherData weatherData = new WeatherData();

        JSONObject main = jsonResponse.getJSONObject("main");
        JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
        JSONObject sys = jsonResponse.getJSONObject("sys");

        double temperature = strategy.convertTemperature(main.getDouble("temp"));
        double humidity = main.getDouble("humidity");
        double pressure = main.getDouble("pressure");
        String description = weather.getString("description");
        String countryCode = sys.getString("country");
        long sunrise = sys.getLong("sunrise");
        long sunset = sys.getLong("sunset");

        weatherData.updateData(temperature, humidity, pressure, description, countryCode, sunrise, sunset);
        return weatherData;
    }
}
