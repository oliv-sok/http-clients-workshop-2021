package pl.allegrotech.weatherapp.domain;

import java.util.Optional;

public interface WeatherRepository {

    void save(Weather weather);

    Optional<Weather> getWeatherByLocation(Location location);

    void deleteAll();

}
