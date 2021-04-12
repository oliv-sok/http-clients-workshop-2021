package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherApiRequest;
import pl.allegrotech.weatherapp.api.WeatherApiResponse;

import java.util.ArrayList;
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

    public void saveWeather(WeatherApiRequest request) {
        weatherRepository.save(apiRequestToWeather(request));
    }

    public List<Weather> getAllWeather() {
        return weatherRepository.getAll().orElse(new ArrayList<>());
    }

    private Weather apiRequestToWeather(WeatherApiRequest request) {
        return new Weather(new Location(request.getLatitude(), request.getLongitude()), request.getCity(), request.getTemperature());
    }
}
