package pl.allegrotech.weatherapp.domain;

public class SampleWeather {

    private static final Location LOCATION = new Location(52.2297, 21.0122);
    private static final Double TEMPERATURE = 21.0;

    public static Weather weatherForWarsaw() {
        return new Weather(LOCATION, TEMPERATURE);
    }

}
