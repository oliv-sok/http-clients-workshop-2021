package pl.allegrotech.weatherapp.domain;

import org.junit.jupiter.api.Test;
import pl.allegrotech.weatherapp.infrastructure.repository.InMemoryWeatherRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.allegrotech.weatherapp.domain.SampleWeather.weatherForWarsaw;

class WeatherServiceTest {

    WeatherRepository weatherRepository = new InMemoryWeatherRepository();
    WeatherService weatherService = new WeatherService(weatherRepository, null);

    @Test
    public void shouldReturnWeatherWhenItExists() {
        // given
        Weather sampleWeather = weatherForWarsaw();
        weatherRepository.save(sampleWeather);

        // when
        Weather foundWeather = weatherService.getWeatherByLocation(sampleWeather.getLocation());

        // then
        assertEquals(sampleWeather, foundWeather);
    }

}
