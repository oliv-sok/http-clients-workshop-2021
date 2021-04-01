package pl.allegrotech.weatherapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WeatherDto {

    private final Double latitude;
    private final Double longitude;
    private final Double temperature;

    public WeatherDto(Double latitude, Double longitude, Double temperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDto that = (WeatherDto) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, temperature);
    }
}
