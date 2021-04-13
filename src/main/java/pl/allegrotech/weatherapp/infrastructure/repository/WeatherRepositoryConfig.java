package pl.allegrotech.weatherapp.infrastructure.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.allegrotech.weatherapp.domain.WeatherRepository;

@Configuration
class WeatherRepositoryConfig {

    @Bean
    WeatherRepository weatherRepository() {
        return new InMemoryWeatherRepository();
    }
}
