package controller;

import patterns.strategy.WeatherProcessingStrategy;

//Strategy
public class ImperialProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        return (kelvin - 273.15) * (9.0/5.0) + 32;
    }

    @Override
    public String getTemperatureUnit() {
        return "°F";
    }
}