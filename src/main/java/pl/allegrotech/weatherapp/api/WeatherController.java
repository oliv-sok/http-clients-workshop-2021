package pl.allegrotech.weatherapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

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
        return ResponseEntity.ok(
                weatherService.getWeatherByLocation(new Location(latitude, longitude)).toApiResponse()
        );
    }

    @GetMapping(path = "/allweather", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<WeatherApiResponse>> getWeather() {
        return ResponseEntity.ok(
                weatherService.getAllWeather().stream().map(Weather::toApiResponse).collect(Collectors.toList())
        );
    }

    @PostMapping(path = "/weather", produces = TEXT_PLAIN_VALUE)
    ResponseEntity<String> addWeatherForLocation(@RequestBody WeatherApiRequest request) {
        logger.info("Set weather for city={}", request.getCity());
        weatherService.saveWeather(request);
        return ResponseEntity.ok("OK");
    }
}
