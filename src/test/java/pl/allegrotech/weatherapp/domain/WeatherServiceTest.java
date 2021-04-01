package pl.allegrotech.weatherapp.domain;

import org.junit.jupiter.api.Test;
import pl.allegrotech.weatherapp.infrastructure.InMemoryWeatherRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.allegrotech.weatherapp.domain.SampleWeather.weatherForWarsaw;

class WeatherServiceTest {

    WeatherRepository weatherRepository = new InMemoryWeatherRepository();
    WeatherService weatherService = new WeatherService(weatherRepository);

    @Test
    public void shouldReturnWeatherWhenItExists() {
        // given
        var sampleWeather = weatherForWarsaw();
        weatherRepository.save(sampleWeather);

        // when
        var foundWeather = weatherService.getWeatherByLocation(sampleWeather.getLocation());

        // then
        assertEquals(sampleWeather, foundWeather);
    }
}
