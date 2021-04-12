package pl.allegrotech.weatherapp.domain;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    void save(Weather weather);

    Optional<Weather> getWeatherByLocation(Location location);

    void deleteAll();

    public Optional<List<Weather>> getAll();
}
