package pl.allegrotech.weatherapp.domain;

public interface WeatherForecastProvider {

    WeatherForecast getWeatherForecastByLocation(Location location);

}
