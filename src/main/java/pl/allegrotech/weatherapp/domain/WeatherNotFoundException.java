package pl.allegrotech.weatherapp.domain;

public class WeatherNotFoundException extends RuntimeException {

    public WeatherNotFoundException(Location location) {
        super(String.format("Weather for location = %s was not found", location));
    }

}
