package pl.allegrotech.weatherapp.api;

import org.springframework.http.HttpStatus;

class WebExceptionResponse {

    private final String errorCode;
    private final String message;

    WebExceptionResponse(HttpStatus httpStatus, String message) {
        this.errorCode = httpStatus != null ? httpStatus.name() : null;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
