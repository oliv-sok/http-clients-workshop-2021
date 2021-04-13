package pl.allegrotech.weatherapp.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        Weather sampleWeather = weatherForWarsaw();
        weatherRepository.save(sampleWeather);
        String url = prepareLocalUrl(sampleWeather.getLocation());

        // when
        ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(url, WeatherApiResponse.class);

        // then
        assertEquals(OK, response.getStatusCode());

        // and
        assertEquals(sampleWeather.toApiResponse(), response.getBody());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenWeatherIsNotAvailableForGivenLocation() {
        // given
        Weather sampleWeather = weatherForWarsaw();
        String url = prepareLocalUrl(sampleWeather.getLocation());

        // when
        ResponseEntity<WebExceptionResponse> response = restTemplate.getForEntity(url, WebExceptionResponse.class);

        // then
        assertEquals(NOT_FOUND, response.getStatusCode());

        // and
        WebExceptionResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(NOT_FOUND.value(), responseBody.getErrorCode());
        assertEquals(
                "Weather for location = Location{latitude=52.2297, longitude=21.0122} was not found",
                responseBody.getMessage()
        );
    }

    private String prepareLocalUrl(Location location) {
        String getWeatherPath = String.format(
                "/weather?latitude=%s&longitude=%s",
                location.getLatitude(),
                location.getLongitude()
        );
        return localUrl(getWeatherPath);
    }

}
