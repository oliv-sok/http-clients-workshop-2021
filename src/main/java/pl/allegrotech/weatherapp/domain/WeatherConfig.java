package pl.allegrotech.weatherapp.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WeatherConfig {

    @Bean
    WeatherService weatherService(WeatherRepository weatherRepository) {
        return new WeatherService(weatherRepository);
    }

}
