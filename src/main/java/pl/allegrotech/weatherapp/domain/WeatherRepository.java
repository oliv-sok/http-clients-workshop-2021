package pl.allegrotech.weatherapp.domain;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    Weather save(Weather weather);

    Optional<Weather> getWeatherByLocation(Location location);

    void deleteAll();

    List<Weather> getAll();
}
