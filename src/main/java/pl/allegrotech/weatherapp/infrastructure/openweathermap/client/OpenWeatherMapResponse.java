package pl.allegrotech.weatherapp.infrastructure.openweathermap.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class OpenWeatherMapResponse {

    private final List<DailyForecastDto> dailyForecastDto;

    public OpenWeatherMapResponse(@JsonProperty(value = "daily") List<DailyForecastDto> dailyForecastDto) {
        this.dailyForecastDto = dailyForecastDto;
    }

    public List<DailyForecastDto> getDailyForecastDto() {
        return dailyForecastDto;
    }

    public static final class DailyForecastDto {

        private final long dateAsUnixTimestamp;
        private final TemperatureForecastDto temperatureForecastDto;

        public DailyForecastDto(
                @JsonProperty(value = "dt") long dateAsUnixTimestamp,
                @JsonProperty(value = "temp") TemperatureForecastDto temperatureForecastDto
        ) {
            this.dateAsUnixTimestamp = dateAsUnixTimestamp;
            this.temperatureForecastDto = temperatureForecastDto;
        }

        public long getDateAsUnixTimestamp() {
            return dateAsUnixTimestamp;
        }

        public TemperatureForecastDto getTemperatureForecastDto() {
            return temperatureForecastDto;
        }

    }

    public static final class TemperatureForecastDto {

        private final double value;

        public TemperatureForecastDto(@JsonProperty(value = "day") double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

    }
}
