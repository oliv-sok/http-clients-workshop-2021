package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherApiRequest;

import java.util.List;

public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherByLocation(Location location) {
        return weatherRepository.getWeatherByLocation(location)
                .orElseThrow(() -> new WeatherNotFoundException(location));
    }

    public Weather saveWeather(WeatherApiRequest request) {
        return weatherRepository.save(request.toWeather());
    }

    public List<Weather> getWeatherForAllLocation() {
        return weatherRepository.getAll();
    }
}
