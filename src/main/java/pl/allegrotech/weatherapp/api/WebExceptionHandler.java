package pl.allegrotech.weatherapp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class WebExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(WeatherNotFoundException.class)
    ResponseEntity<ExceptionResponse> handleWeatherNotFoundException(WeatherNotFoundException exception) {
        var response = new ExceptionResponse(NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    public static final class ExceptionResponse {

        private final int errorCode;
        private final String message;

        public ExceptionResponse(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }

}
