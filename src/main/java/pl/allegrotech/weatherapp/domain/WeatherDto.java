package pl.allegrotech.weatherapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WeatherDto {

    private final Double latitude;
    private final Double longitude;
    private final Double temperature;
    private final String city;

    public WeatherDto(Double latitude, Double longitude, Double temperature, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.city = city;
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

    public String getCity() { return city; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherDto that = (WeatherDto) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude)
                && Objects.equals(temperature, that.temperature) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, temperature, city);
    }
}
