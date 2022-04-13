package pl.allegrotech.weatherapp.infrastructure.openweathermap.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class OpenWeatherMapResponse {

    // TODO Zadanie 2
    private final List<DaillyForecastDTO> daillyForecastDTO;

    public OpenWeatherMapResponse(@JsonProperty("daily") List<DaillyForecastDTO> daillyForecastDTO) {
        this.daillyForecastDTO = daillyForecastDTO;
    }

    public List<DaillyForecastDTO> getDaillyForecastDTO() {
        return daillyForecastDTO;
    }

    public final static class DaillyForecastDTO{
        private final long dt;
        private final TemperatureForecastDTO temperatureForecastDTO;

        public DaillyForecastDTO(@JsonProperty("dt") long dt,
                                  @JsonProperty("temp")TemperatureForecastDTO temperatureForecastDTO) {
            this.dt = dt;
            this.temperatureForecastDTO = temperatureForecastDTO;
        }

        public long getDt() {
            return dt;
        }

        public TemperatureForecastDTO getTemperatureForecastDTO() {
            return temperatureForecastDTO;
        }
    }

    public final static class TemperatureForecastDTO{
        private final double value;

        public TemperatureForecastDTO(@JsonProperty("day") double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

}
