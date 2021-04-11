package pl.allegrotech.weatherapp.api;

class WebExceptionResponse {

    private final int errorCode;
    private final String message;

    WebExceptionResponse(int errorCode, String message) {
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
