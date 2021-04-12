package pl.allegrotech.weatherapp.domain;

import java.util.Optional;

public interface WeatherRepository {

    void save(Weather weather);

    Optional<Weather> getWeatherByLocation(Weather.Location location);

    Optional<Weather> getWeatherByCity(String city);

    void deleteAll();

}
