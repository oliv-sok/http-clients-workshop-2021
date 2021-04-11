package pl.allegrotech.weatherapp.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WeatherApiResponse {

    private final double latitude;
    private final double longitude;
    private final double temperature;

    public WeatherApiResponse(double latitude, double longitude, double temperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherApiResponse that = (WeatherApiResponse) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.temperature, temperature) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, temperature);
    }

    @Override
    public String toString() {
        return "WeatherApiResponse{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", temperature=" + temperature +
                '}';
    }
}
