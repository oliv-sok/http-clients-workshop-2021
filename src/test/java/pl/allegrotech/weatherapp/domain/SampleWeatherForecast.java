package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.domain.WeatherForecast.DailyTemperature;

import java.util.List;

import static java.time.Instant.ofEpochSecond;

public class SampleWeatherForecast {

    public static WeatherForecast weatherForecastForWarsaw() {
        return new WeatherForecast(
                new Location(52.5, 21.5),
                List.of(
                        new DailyTemperature(ofEpochSecond(1618311600), 20),
                        new DailyTemperature(ofEpochSecond(1618398000), 21),
                        new DailyTemperature(ofEpochSecond(1618484400), 22),
                        new DailyTemperature(ofEpochSecond(1618570800), 23),
                        new DailyTemperature(ofEpochSecond(1618657200), 24),
                        new DailyTemperature(ofEpochSecond(1618743600), 25),
                        new DailyTemperature(ofEpochSecond(1618830000), 26),
                        new DailyTemperature(ofEpochSecond(1618916400), 27)
                )
        );
    }

}