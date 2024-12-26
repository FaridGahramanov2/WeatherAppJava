package model;

import java.util.HashMap;
import java.util.Map;

public class WeatherDataCache {
    private static WeatherDataCache instance;
    private final Map<String, CacheEntry> cache;
    private static final long CACHE_DURATION = 1800000;

    private WeatherDataCache() {
        cache = new HashMap<>();
    }

    public static WeatherDataCache getInstance() {
        if (instance == null) {
            synchronized (WeatherDataCache.class) {
                if (instance == null) {
                    instance = new WeatherDataCache();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && !entry.isExpired()) {
            return entry.getData();
        }
        return null;
    }

    public void put(String key, String data) {
        cache.put(key, new CacheEntry(data));
    }

    private static class CacheEntry {
        private final String data;
        private final long timestamp;

        CacheEntry(String data) {
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }

        String getData() {
            return data;
        }

        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > CACHE_DURATION;
        }
    }
}