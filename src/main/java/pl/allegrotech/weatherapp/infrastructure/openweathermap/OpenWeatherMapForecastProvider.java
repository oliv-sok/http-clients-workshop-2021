package pl.allegrotech.weatherapp.infrastructure.openweathermap;

import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.WeatherForecast;
import pl.allegrotech.weatherapp.domain.WeatherForecastProvider;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapClient;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapResponse;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapResponse.DailyForecastDto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

class OpenWeatherMapForecastProvider implements WeatherForecastProvider {

    private final OpenWeatherMapClient openWeatherMapClient;

    OpenWeatherMapForecastProvider(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    @Override
    public WeatherForecast getWeatherForecastByLocation(Location location) {
        // TODO Zadanie 2
        OpenWeatherMapResponse openWeatherMapResponse = openWeatherMapClient.getWeatherForecast(location);
        return toWeatherForecast(location, openWeatherMapResponse.getDailyForecastDto());
    }

    private WeatherForecast toWeatherForecast(Location location, List<DailyForecastDto> dailyForecastDto) {
        List<WeatherForecast.DailyTemperature> dailyTemperatures = dailyForecastDto.stream()
                .map(this::toDailyTemperature)
                .collect(Collectors.toList());
        return new WeatherForecast(location, dailyTemperatures);
    }

    private WeatherForecast.DailyTemperature toDailyTemperature(DailyForecastDto dailyForecastDto) {
        return new WeatherForecast.DailyTemperature(
                Instant.ofEpochSecond(dailyForecastDto.getDateAsUnixTimestamp()),
                dailyForecastDto.getTemperatureForecastDto().getValue()
        );
    }

}
