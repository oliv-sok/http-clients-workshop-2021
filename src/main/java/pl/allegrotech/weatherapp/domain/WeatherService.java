package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherNotFoundException;

public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherByLocation(Weather.Location location) {
        return weatherRepository.getWeatherByLocation(location)
                .orElseThrow(() -> new WeatherNotFoundException(location));
    }

}
