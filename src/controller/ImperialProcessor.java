package controller;

import patterns.strategy.WeatherProcessingStrategy;

public class ImperialProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        // Correct formula for Kelvin to Fahrenheit: °F = (K - 273.15) × 9/5 + 32
        return (kelvin - 273.15) * (9.0/5.0) + 32;
    }

    @Override
    public String getTemperatureUnit() {
        return "°F";
    }
}