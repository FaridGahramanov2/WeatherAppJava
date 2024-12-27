package controller;


import patterns.command.Command;
import patterns.strategy.WeatherProcessingStrategy;

//Command
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
        System.out.println("Processing strategy updated to: " + strategy.getClass().getSimpleName());
    }
}