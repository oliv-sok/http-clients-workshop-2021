package pl.allegrotech.weatherapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.WeatherService;

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
        return ResponseEntity.ok(
                weatherService.getWeatherByLocation(new Location(latitude, longitude)).toApiResponse()
        );
    }

    // TODO Zadanie 1

}
