package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherApiResponse;

import java.util.Objects;

public final class Weather {

    private final Location location;
    private final String city;
    private final double temperature;

    public Weather(Location location, String city, double temperature) {
        this.location = location;
        this.city = city;
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public WeatherApiResponse toApiResponse() {
        Location location = this.getLocation();
        return new WeatherApiResponse(
                location.getLatitude(),
                location.getLongitude(),
                this.getCity(),
                this.getTemperature());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Double.compare(weather.temperature, temperature) == 0 && Objects.equals(city, weather.city) && Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, city, temperature);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location=" + location +
                ", temperature=" + temperature +
                '}';
    }
}
