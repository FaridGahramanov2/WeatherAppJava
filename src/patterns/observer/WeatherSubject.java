package patterns.observer;


import patterns.observer.WeatherObserver;

public interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
}