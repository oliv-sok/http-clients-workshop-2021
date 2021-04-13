package pl.allegrotech.weatherapp.infrastructure.repository;

import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

import java.util.*;

public class InMemoryWeatherRepository implements WeatherRepository {

    private final Map<Location, Weather> storage = new HashMap<>();

    private static final List<Weather> initialWeatherList = List.of(
            new Weather(new Location(18, 53), "Toruń", 15),
            new Weather(new Location(23, 53), "Białystok", 9),
            new Weather(new Location(20, 49), "Zakopane", 13),
            new Weather(new Location(14, 53), "Szczecin", 14),
            new Weather(new Location(22, 49), "Wetlina", 11)
    );

    public InMemoryWeatherRepository() {
        initialWeatherList.forEach(this::save);
    }

    @Override
    public Weather save(Weather weather) {
        storage.put(weather.getLocation(), weather);
        return weather;
    }

    @Override
    public Optional<Weather> getWeatherByLocation(Location location) {
        return Optional.ofNullable(storage.get(location));
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public List<Weather> getAll() {
        return new ArrayList<>(storage.values());
    }

}
