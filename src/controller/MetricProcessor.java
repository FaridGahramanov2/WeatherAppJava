package controller;

import patterns.strategy.WeatherProcessingStrategy;

//Strateg
public class MetricProcessor implements WeatherProcessingStrategy {
    @Override
    public double convertTemperature(double kelvin) {
        return kelvin - 273.15;
    }

    @Override
    public String getTemperatureUnit() {
        return "°C";
    }
}