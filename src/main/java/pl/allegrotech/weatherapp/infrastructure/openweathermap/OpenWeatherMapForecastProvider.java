package pl.allegrotech.weatherapp.infrastructure.openweathermap;

import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.WeatherForecast;
import pl.allegrotech.weatherapp.domain.WeatherForecastProvider;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapClient;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapResponse;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static pl.allegrotech.weatherapp.domain.WeatherForecast.*;

class OpenWeatherMapForecastProvider implements WeatherForecastProvider {

    private final OpenWeatherMapClient openWeatherMapClient;

    OpenWeatherMapForecastProvider(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    @Override
    public WeatherForecast getWeatherForecastByLocation(Location location) {
        // TODO Zadanie 2
        OpenWeatherMapResponse openWeatherMapResponse = openWeatherMapClient.getWeatherForecast(location);

        List<DailyTemperature> dailyTemperatures = openWeatherMapResponse
                .getDaillyForecastDTO()
                .stream()
                .map(x -> new DailyTemperature(
                            Instant.ofEpochSecond(x.getDt()),
                            x.getTemperatureForecastDTO().getValue()))
                .collect(Collectors.toList());

        return new WeatherForecast(
                location,
                dailyTemperatures

        );
    }

}
