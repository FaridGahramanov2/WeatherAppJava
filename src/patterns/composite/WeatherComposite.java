package patterns.composite;

        import java.util.ArrayList;
        import java.util.List;

public class WeatherComposite implements WeatherComponent {
    private List<WeatherComponent> components = new ArrayList<>();

    public void add(WeatherComponent component) {
        components.add(component);
    }

    public void remove(WeatherComponent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        for (WeatherComponent component : components) {
            component.display();
        }
    }

    @Override
    public double getTemperature() {
        if (components.isEmpty()) return 0;
        double sum = 0;
        for (WeatherComponent component : components) {
            sum += component.getTemperature();
        }
        return sum / components.size();
    }

    @Override
    public int getHumidity() {
        if (components.isEmpty()) return 0;
        int sum = 0;
        for (WeatherComponent component : components) {
            sum += component.getHumidity();
        }
        return sum / components.size();
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder();
        for (WeatherComponent component : components) {
            desc.append(component.getDescription()).append(", ");
        }
        return desc.length() > 0 ? desc.substring(0, desc.length() - 2) : "";
    }
}