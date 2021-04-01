package pl.allegrotech.weatherapp.api;

import pl.allegrotech.weatherapp.domain.Weather;

public class WeatherNotFoundException extends RuntimeException {

    public WeatherNotFoundException(Weather.Location location) {
        super(String.format("Weather with location = %s was not found", location));
    }

}
