package patterns.composite;

public interface WeatherComponent {
    void display();
    double getTemperature();
    int getHumidity();
    String getDescription();
}
