package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherApiResponse;

import java.util.Objects;

public final class Weather {

    private final Location location;
    private final double temperature;

    public Weather(Location location, double temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public WeatherApiResponse toApiResponse() {
        Location location = this.getLocation();
        return new WeatherApiResponse(
                location.getLatitude(),
                location.getLongitude(),
                this.getTemperature());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Double.compare(weather.temperature, temperature) == 0 && Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, temperature);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location=" + location +
                ", temperature=" + temperature +
                '}';
    }
}
