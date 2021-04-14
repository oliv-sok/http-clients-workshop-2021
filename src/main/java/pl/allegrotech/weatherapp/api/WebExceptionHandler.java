package pl.allegrotech.weatherapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.allegrotech.weatherapp.domain.WeatherNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(WeatherNotFoundException.class)
    ResponseEntity<WebExceptionResponse> handleWeatherNotFoundException(WeatherNotFoundException exception) {
        WebExceptionResponse response = new WebExceptionResponse(NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

}
