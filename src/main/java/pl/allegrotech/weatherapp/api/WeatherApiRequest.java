package pl.allegrotech.weatherapp.api;

public class WeatherApiRequest {
    private final double latitude;
    private final double longitude;
    private final String city;
    private final double temperature;

    public WeatherApiRequest(double latitude, double longitude, String city, double temperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.temperature = temperature;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }
}
