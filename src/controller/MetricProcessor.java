package controller;

import patterns.strategy.WeatherProcessingStrategy;

public class MetricProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        // Convert Kelvin to Celsius
        return kelvin - 273.15;
    }

    @Override
    public String getTemperatureUnit() {
        return "°C";
    }
}