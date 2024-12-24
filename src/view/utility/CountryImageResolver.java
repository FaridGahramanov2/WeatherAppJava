package view.utility;

import java.io.File;

public class CountryImageResolver {
    private static final String IMAGE_BASE_PATH = "assets/images/countries/";

    public static String getCountryImagePath(String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            return IMAGE_BASE_PATH + "default.png";
        }

        String filePath = IMAGE_BASE_PATH + countryCode.toLowerCase() + ".png";
        File file = new File(filePath);


        if (file.exists()) {
            return filePath;
        } else {
            return IMAGE_BASE_PATH + "default.png";
        }
    }
}
