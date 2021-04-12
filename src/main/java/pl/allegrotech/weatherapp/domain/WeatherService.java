package pl.allegrotech.weatherapp.domain;

public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherByLocation(Location location) {
        return weatherRepository.getWeatherByLocation(location)
                .orElseThrow(() -> new WeatherNotFoundException(location));
    }
}
