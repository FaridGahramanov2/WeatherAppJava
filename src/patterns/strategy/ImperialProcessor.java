package patterns.strategy;

public class ImperialProcessor implements WeatherProcessingStrategy {

    @Override
    public double convertTemperature(double kelvin) {
        // Convert Kelvin to Fahrenheit
        return (kelvin - 273.15) * 9/5 + 32;
    }

    @Override
    public String getTemperatureUnit() {
        return "°F";
    }
}