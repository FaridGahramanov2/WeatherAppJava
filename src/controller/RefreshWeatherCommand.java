package controller;

import controller.WeatherController;
import patterns.command.Command;

public class RefreshWeatherCommand implements Command {
    private final WeatherController controller;
    private final String city;

    public RefreshWeatherCommand(WeatherController controller, String city) {
        this.controller = controller;
        this.city = city;
    }

    @Override
    public void execute() {
        controller.fetchWeatherData(city);
    }
}