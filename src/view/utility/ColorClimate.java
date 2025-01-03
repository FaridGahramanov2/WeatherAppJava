package view.utility;

import model.WeatherConditions;
import java.awt.Color;


public class ColorClimate {
    private final Color[] colorPalette;

    public ColorClimate(WeatherConditions condition, double temperature, long currentTime, long sunrise, long sunset) {
        boolean isDaytime = currentTime >= sunrise && currentTime <= sunset;

        if (condition == null) {
            this.colorPalette = new Color[]{new Color(0x8E9EAB), new Color(0xEFEFEF)};
        } else {
            switch (condition) {
                case CLEAR:
                    if (isDaytime) {
                        this.colorPalette = (temperature > 30)
                                ? new Color[]{new Color(0xFDB813), new Color(0xFF4E00)}
                                : new Color[]{new Color(0x87CEEB), new Color(0x4682B4)};
                    } else {
                        this.colorPalette = new Color[]{new Color(0x1a237e), new Color(0x000051)};
                    }
                    break;
                case RAIN:
                    this.colorPalette = isDaytime
                            ? new Color[]{new Color(0x4C6EF5), new Color(0x182848)}
                            : new Color[]{new Color(0x1a1a1a), new Color(0x000000)};
                    break;
                case SNOW:
                    this.colorPalette = isDaytime
                            ? new Color[]{new Color(0xDDEBF7), new Color(0xA4BED8)}
                            : new Color[]{new Color(0x9FA8DA), new Color(0x534bae)};
                    break;
                case CLOUDS:
                    this.colorPalette = isDaytime
                            ? new Color[]{new Color(0x757F9A), new Color(0xD7DDE8)}
                            : new Color[]{new Color(0x303030), new Color(0x1a1a1a)};
                    break;
                case THUNDERSTORM:
                    this.colorPalette = new Color[]{new Color(0x434343), new Color(0x000000)};
                    break;
                default:
                    this.colorPalette = new Color[]{new Color(0x8E9EAB), new Color(0xEFEFEF)};
                    break;
            }
        }
    }

    public Color[] getColorPalette() {
        return colorPalette;
    }
}