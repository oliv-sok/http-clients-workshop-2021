package pl.allegrotech.weatherapp.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiRequest {

    private final double latitude;
    private final double longitude;
    private final String city;
    private final double temperature;

    public WeatherApiRequest(double latitude, double longitude, String city, double temperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.temperature = temperature;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public Weather toWeather() {
        return new Weather(
                new Location(
                        this.getLatitude(),
                        this.getLongitude()
                ),
                this.getCity(),
                this.getTemperature()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherApiRequest that = (WeatherApiRequest) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.temperature, temperature) == 0 && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, city, temperature);
    }

    @Override
    public String toString() {
        return "WeatherApiRequest{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                '}';
    }

}
