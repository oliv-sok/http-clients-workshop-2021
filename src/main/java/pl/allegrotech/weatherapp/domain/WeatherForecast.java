package pl.allegrotech.weatherapp.domain;

import pl.allegrotech.weatherapp.api.WeatherForecastApiResponse;
import pl.allegrotech.weatherapp.api.WeatherForecastApiResponse.DailyTemperatureApiResponse;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class WeatherForecast {

    private final Location location;
    private final List<DailyTemperature> dailyTemperatures;

    public WeatherForecast(Location location, List<DailyTemperature> dailyTemperatures) {
        this.location = location;
        this.dailyTemperatures = dailyTemperatures;
    }

    public Location getLocation() {
        return location;
    }

    public List<DailyTemperature> getDailyTemperatures() {
        return dailyTemperatures;
    }

    public WeatherForecastApiResponse toApiResponse() {
        List<DailyTemperatureApiResponse> dailyTemperatures = this.dailyTemperatures.stream()
                .map(temperature -> new DailyTemperatureApiResponse(temperature.getDate(), temperature.getValue()))
                .collect(Collectors.toList());
        return new WeatherForecastApiResponse(location.getLatitude(), location.getLongitude(), dailyTemperatures);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecast that = (WeatherForecast) o;
        return Objects.equals(location, that.location) && Objects.equals(dailyTemperatures, that.dailyTemperatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, dailyTemperatures);
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "location=" + location +
                ", dailyTemperatures=" + dailyTemperatures +
                '}';
    }

    public static final class DailyTemperature {

        private final Instant date;
        private final double value;

        public DailyTemperature(Instant date, double temperature) {
            this.date = date;
            this.value = temperature;
        }

        public Instant getDate() {
            return date;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DailyTemperature that = (DailyTemperature) o;
            return Double.compare(that.value, value) == 0 && Objects.equals(date, that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, value);
        }

        @Override
        public String toString() {
            return "DailyTemperature{" +
                    "date=" + date +
                    ", value=" + value +
                    '}';
        }
    }
}
