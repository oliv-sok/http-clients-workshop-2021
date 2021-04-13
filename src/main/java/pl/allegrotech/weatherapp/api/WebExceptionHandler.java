package pl.allegrotech.weatherapp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.allegrotech.weatherapp.domain.WeatherNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class WebExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(WeatherNotFoundException.class)
    ResponseEntity<WebExceptionResponse> handleWeatherNotFoundException(WeatherNotFoundException exception) {
        WebExceptionResponse response = new WebExceptionResponse(NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

}
