package model;

import org.json.JSONObject;
import patterns.strategy.WeatherProcessingStrategy;
//Adapter
public class ApiResponseAdapter {
    public WeatherData convertToWeatherData(String jsonString, WeatherProcessingStrategy strategy) {
        try {
            JSONObject jsonResponse = new JSONObject(jsonString);
            WeatherData weatherData = new WeatherData();

            JSONObject main = jsonResponse.getJSONObject("main");
            JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
            JSONObject sys = jsonResponse.getJSONObject("sys");


            double tempKelvin = main.getDouble("temp");
            double temperature = strategy.convertTemperature(tempKelvin);

            double humidity = main.getDouble("humidity");
            double pressure = main.getDouble("pressure");
            String description = weather.getString("description");
            String countryCode = sys.getString("country");
            long sunrise = sys.getLong("sunrise");
            long sunset = sys.getLong("sunset");

            weatherData.updateData(temperature, humidity, pressure, description, countryCode, sunrise, sunset);
            return weatherData;
        } catch (Exception e) {
            throw new RuntimeException("Error converting API response: " + e.getMessage());
        }
    }
}