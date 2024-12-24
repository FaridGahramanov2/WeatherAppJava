package patterns.command;

import controller.WeatherController;
import patterns.strategy.WeatherProcessingStrategy;

public class SaveSettingsCommand implements Command {
    private final WeatherController controller;
    private final WeatherProcessingStrategy strategy;

    public SaveSettingsCommand(WeatherController controller, WeatherProcessingStrategy strategy) {
        this.controller = controller;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        controller.setProcessingStrategy(strategy);
    }
}