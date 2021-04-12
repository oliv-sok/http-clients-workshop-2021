package pl.allegrotech.weatherapp.infrastructure.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.allegrotech.weatherapp.domain.Location;
import pl.allegrotech.weatherapp.domain.Weather;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

import javax.annotation.PostConstruct;

@Configuration
class WeatherRepositoryConfig {

    @Bean
    WeatherRepository weatherRepository() {
        return new InMemoryWeatherRepository();
    }
}
