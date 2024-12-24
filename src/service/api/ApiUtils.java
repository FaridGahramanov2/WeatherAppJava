package service.api;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiUtils {
    public static String buildUrl(String endpoint, String... params) {
        StringBuilder url = new StringBuilder(ApiConfig.getBaseUrl() + endpoint + "?");
        for (String param : params) {
            url.append(URLEncoder.encode(param, StandardCharsets.UTF_8)).append("&");
        }
        url.append("appid=").append(ApiConfig.getApiKey());
        return url.toString();
    }
}