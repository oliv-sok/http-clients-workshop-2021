package pl.allegrotech.weatherapp.domain;

import java.util.Objects;

public final class Weather {

    private final Location location;
    private final Double temperature;
    private final String city;

    public Weather(Location location, Double temperature, String city) {
        this.location = location;
        this.temperature = temperature;
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public Double getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

    public WeatherDto toDto() {
        var location = this.getLocation();
        return new WeatherDto(
                location.getLatitude(),
                location.getLongitude(),
                this.getTemperature(),
                city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(location, weather.location) && Objects.equals(temperature, weather.temperature);
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

    public static final class Location {

        private final Double latitude;
        private final Double longitude;

        public Location(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return Objects.equals(latitude, location.latitude) && Objects.equals(longitude, location.longitude);
        }

        @Override
        public int hashCode() {
            return Objects.hash(latitude, longitude);
        }

        @Override
        public String toString() {
            return "Location{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
