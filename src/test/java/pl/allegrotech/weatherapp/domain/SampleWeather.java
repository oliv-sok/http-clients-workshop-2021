package pl.allegrotech.weatherapp.domain;

public class SampleWeather {

    public static Weather weatherForWarsaw() {
        return new Weather(new Location(52.5, 21.5), "Warsaw", 21);
    }

}
