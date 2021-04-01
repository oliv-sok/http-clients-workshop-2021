package pl.allegrotech.weatherapp.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import pl.allegrotech.weatherapp.api.WebExceptionHandler.ExceptionResponse;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherRepository;
import pl.allegrotech.weatherapp.domain.WeatherDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static pl.allegrotech.weatherapp.domain.SampleWeather.weatherForWarsaw;

class WeatherControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    public void beforeEach() {
        weatherRepository.deleteAll();
    }

    @Test
    public void shouldReturnStatusOKWhenWeatherIsAvailableForGivenLocation() {
        // given
        var sampleWeather = weatherForWarsaw();
        weatherRepository.save(sampleWeather);

        // when
        String url = prepareLocalUrl(sampleWeather.getLocation());
        ResponseEntity<WeatherDto> response = restTemplate.getForEntity(url, WeatherDto.class);

        // then
        assertEquals(OK, response.getStatusCode());

        // and
        assertEquals(sampleWeather.toDto(), response.getBody());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenWeatherIsNotAvailableForGivenLocation() {
        // given
        var sampleWeather = weatherForWarsaw();

        // when
        String url = prepareLocalUrl(sampleWeather.getLocation());
        ResponseEntity<ExceptionResponse> response = restTemplate.getForEntity(url, ExceptionResponse.class);

        // then
        assertEquals(NOT_FOUND, response.getStatusCode());

        // and
        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(NOT_FOUND.value(), responseBody.getErrorCode());
        assertEquals("Weather with location = Location{latitude=52.2297, longitude=21.0122} was not found", responseBody.getMessage());
    }

    private String prepareLocalUrl(Weather.Location location) {
        String getWeatherPath = String.format(
                "/weather?latitude=%s&longitude=%s",
                location.getLatitude(),
                location.getLongitude()
        );
        return localUrl(getWeatherPath);
    }

}
