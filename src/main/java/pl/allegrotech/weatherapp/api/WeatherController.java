package pl.allegrotech.weatherapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherForecast;
import pl.allegrotech.weatherapp.domain.WeatherService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/weather", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherApiResponse> getWeather(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude
    ) {
        logger.info("Getting weather for latitude = {} and longitude = {}", latitude, longitude);
        Weather weather = weatherService.getWeatherByLocation(new Location(latitude, longitude));
        return ResponseEntity.ok(weather.toApiResponse());
    }

    @GetMapping(path = "/weather/all", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<WeatherApiResponse>> getWeatherForAllLocations() {
        logger.info("Getting weather for all locations");
        List<WeatherApiResponse> weatherForAllLocations = weatherService.getWeatherForAllLocations()
                .stream()
                .map(Weather::toApiResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(weatherForAllLocations);
    }

    @PostMapping(path = "/weather", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherApiResponse> addWeatherForLocation(
            @RequestBody WeatherApiRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        // TODO Zadanie 1
        logger.info("Adding weather = {}", request);
        Weather savedWeather = weatherService.saveWeather(request.toWeather());
        URI createdResourceLocationUri = uriComponentsBuilder.path("/weather")
                .queryParam("latitude", request.getLatitude())
                .queryParam("longitude", request.getLongitude())
                .build().toUri();
        return ResponseEntity
                .created(createdResourceLocationUri)
                .body(savedWeather.toApiResponse());
    }

    @GetMapping(path = "/weather/forecast", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WeatherForecastApiResponse> getWeatherForecast(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude
    ) {
        // TODO Zadanie 2
        logger.info("Getting weather forecast for latitude = {} and longitude = {}", latitude, longitude);
        WeatherForecast weatherForecast = weatherService.getWeatherForecastByLocation(new Location(latitude, longitude));
        return ResponseEntity.ok(weatherForecast.toApiResponse());
    }

}
