// ApiResponseAdapter.java
package patterns.adapter;

import model.WeatherData;
import patterns.strategy.WeatherProcessingStrategy;
import org.json.JSONObject;

public class ApiResponseAdapter {
    public WeatherData convertToWeatherData(String jsonString, WeatherProcessingStrategy strategy) {
        JSONObject jsonResponse = new JSONObject(jsonString);
        WeatherData weatherData = new WeatherData();

        JSONObject main = jsonResponse.getJSONObject("main");
        JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);

        double temperature = strategy.convertTemperature(main.getDouble("temp"));
        double humidity = main.getDouble("humidity");
        double pressure = main.getDouble("pressure");
        String description = weather.getString("description");

        weatherData.updateData(temperature, humidity, pressure, description);
        return weatherData;
    }
}