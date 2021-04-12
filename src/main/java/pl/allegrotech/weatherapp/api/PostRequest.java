package pl.allegrotech.weatherapp.api;

public class PostRequest {
    private String city;

    public PostRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
