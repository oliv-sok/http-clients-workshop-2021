package pl.allegrotech.weatherapp.domain;

import java.util.List;

public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherForecastProvider weatherForecastProvider;

    public WeatherService(WeatherRepository weatherRepository, WeatherForecastProvider weatherForecastProvider) {
        this.weatherRepository = weatherRepository;
        this.weatherForecastProvider = weatherForecastProvider;
    }

    public Weather getWeatherByLocation(Location location) {
        return weatherRepository.getWeatherByLocation(location)
                .orElseThrow(() -> new WeatherNotFoundException(location));
    }

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> getWeatherForAllLocations() {
        return weatherRepository.getAll();
    }

    public WeatherForecast getWeatherForecastByLocation(Location location) {
        return weatherForecastProvider.getWeatherForecastByLocation(location);
    }

}
