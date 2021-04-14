package pl.allegrotech.weatherapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import pl.allegrotech.weatherapp.domain.WeatherNotFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
class WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(WeatherNotFoundException.class)
    ResponseEntity<WebExceptionResponse> handleWeatherNotFoundException(WeatherNotFoundException exception) {
        WebExceptionResponse response = new WebExceptionResponse(NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<WebExceptionResponse> handleHttpClientErrorException(HttpClientErrorException exception) {
        // TODO Zadanie 3
        logger.warn("Client error occurred: ", exception);
        WebExceptionResponse response = new WebExceptionResponse(BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    ResponseEntity<WebExceptionResponse> handleHttpServerErrorException(HttpServerErrorException exception) {
        // TODO Zadanie 3
        logger.error("Server error occurred: ", exception);
        WebExceptionResponse response = new WebExceptionResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAccessException.class)
    ResponseEntity<WebExceptionResponse> handleResourceAccessException(ResourceAccessException exception) {
        // TODO Zadanie 3
        logger.warn("Timeout error occurred: ", exception);
        WebExceptionResponse response = new WebExceptionResponse(REQUEST_TIMEOUT, exception.getMessage());
        return new ResponseEntity<>(response, REQUEST_TIMEOUT);
    }

}
