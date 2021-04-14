package pl.allegrotech.weatherapp.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class WeatherForecastApiResponse {

    private final double latitude;
    private final double longitude;
    private final List<DailyTemperatureApiResponse> dailyTemperatures;

    public WeatherForecastApiResponse(double latitude, double longitude, List<DailyTemperatureApiResponse> dailyTemperatures) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.dailyTemperatures = dailyTemperatures;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<DailyTemperatureApiResponse> getDailyTemperatures() {
        return dailyTemperatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecastApiResponse that = (WeatherForecastApiResponse) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(dailyTemperatures, that.dailyTemperatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, dailyTemperatures);
    }

    @Override
    public String toString() {
        return "WeatherForecastApiResponse{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", dailyTemperatures=" + dailyTemperatures +
                '}';
    }

    public static final class DailyTemperatureApiResponse {

        private final Instant date;
        private final double value;

        public DailyTemperatureApiResponse(Instant date, double value) {
            this.date = date;
            this.value = value;
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
            DailyTemperatureApiResponse that = (DailyTemperatureApiResponse) o;
            return Double.compare(that.value, value) == 0 && Objects.equals(date, that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, value);
        }

        @Override
        public String toString() {
            return "DailyTemperatureApiResponse{" +
                    "date=" + date +
                    ", value=" + value +
                    '}';
        }
    }
}
