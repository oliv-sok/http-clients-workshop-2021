package pl.allegrotech.weatherapp.infrastructure.repository;

import org.springframework.stereotype.Component;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

import javax.annotation.PostConstruct;

@Component
public class RepositoryInit {
    private final WeatherRepository weatherRepository;

    public RepositoryInit(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @PostConstruct
    void createStartingData() {
        Weather weather1 = new Weather(new Location(18, 53), "Toruń", 15);
        Weather weather2 = new Weather(new Location(23, 53), "Białystok", 9);
        Weather weather3 = new Weather(new Location(20, 49), "Zakopane", 13);
        Weather weather4 = new Weather(new Location(14, 53), "Szczecin", 14);
        Weather weather5 = new Weather(new Location(22, 49), "Wetlina", 11);

        weatherRepository.save(weather1);
        weatherRepository.save(weather2);
        weatherRepository.save(weather3);
        weatherRepository.save(weather4);
        weatherRepository.save(weather5);
    }
}
