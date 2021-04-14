package pl.allegrotech.weatherapp.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherForecast;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pl.allegrotech.weatherapp.domain.SampleWeather.weatherForWarsaw;
import static pl.allegrotech.weatherapp.domain.SampleWeatherForecast.weatherForecastForWarsaw;

class WeatherControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    TestRestTemplate restTemplate;

    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().port(8888));

    @BeforeEach
    public void beforeEach() {
        weatherRepository.deleteAll();
        wireMockServer.start();
    }

    @AfterEach
    public void afterEach() {
        wireMockServer.stop();
    }

    @Test
    public void shouldReturnStatusOKWhenWeatherIsAvailableForGivenLocation() {
        // given
        Weather sampleWeather = weatherForWarsaw();
        weatherRepository.save(sampleWeather);
        String url = prepareLocalUrlForSingleWeather(sampleWeather.getLocation());

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
        String url = prepareLocalUrlForSingleWeather(sampleWeather.getLocation());

        // when
        ResponseEntity<WebExceptionResponse> response = restTemplate.getForEntity(url, WebExceptionResponse.class);

        // then
        assertEquals(NOT_FOUND, response.getStatusCode());

        // and
        WebExceptionResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(NOT_FOUND.name(), responseBody.getErrorCode());
        assertEquals(
                "Weather for location = Location{latitude=52.5, longitude=21.5} was not found",
                responseBody.getMessage()
        );
    }

    @Test
    public void shouldAddWeatherForGivenLocation() {
        // given
        Weather sampleWeather = weatherForWarsaw();

        // when
        ResponseEntity<WeatherApiResponse> response = restTemplate.postForEntity(
                "/weather",
                sampleWeather.toApiRequest(),
                WeatherApiResponse.class
        );

        // then
        assertEquals(CREATED, response.getStatusCode());

        // and
        assertEquals(sampleWeather.toApiResponse(), response.getBody());
        assertEquals(
                String.format("http://localhost:%s/weather?latitude=52.5&longitude=21.5", port),
                response.getHeaders().getLocation().toString()
        );

        // and
        List<Weather> weather = weatherRepository.getAll();
        assertEquals(1, weather.size());
        assertEquals(sampleWeather, weather.get(0));
    }

    @Test
    public void shouldReturnStatusOKWhenWeatherForecastIsAvailableForGivenLocation() {
        // given
        WeatherForecast sampleWeatherForecast = weatherForecastForWarsaw();
        String url = prepareLocalUrlForWeatherForecast(sampleWeatherForecast.getLocation());

        // and
        wireMockServer.stubFor(get(urlPathMatching(".*/data/2.5/onecall.*"))
                .willReturn(aResponse()
                        .withBodyFile("openWeatherMapStub.json")
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withStatus(OK.value())));

        // when
        ResponseEntity<WeatherForecastApiResponse> response = restTemplate.getForEntity(url, WeatherForecastApiResponse.class);

        // then
        assertEquals(OK, response.getStatusCode());

        // and
        assertEquals(sampleWeatherForecast.toApiResponse(), response.getBody());
    }

    private String prepareLocalUrlForSingleWeather(Location location) {
        String getWeatherPath = String.format(
                "/weather?latitude=%s&longitude=%s",
                location.getLatitude(),
                location.getLongitude()
        );
        return localUrl(getWeatherPath);
    }

    private String prepareLocalUrlForWeatherForecast(Location location) {
        String getWeatherPath = String.format(
                "/weather/forecast?latitude=%s&longitude=%s",
                location.getLatitude(),
                location.getLongitude()
        );
        return localUrl(getWeatherPath);
    }

}
