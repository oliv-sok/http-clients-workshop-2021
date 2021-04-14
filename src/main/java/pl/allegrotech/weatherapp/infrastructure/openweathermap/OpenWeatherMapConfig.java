package pl.allegrotech.weatherapp.infrastructure.openweathermap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.allegrotech.weatherapp.domain.WeatherForecastProvider;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapClient;

import java.time.Duration;

@Configuration
class OpenWeatherMapConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

    @Bean
    public OpenWeatherMapClient openWeatherMapClient(
            RestTemplate restTemplate,
            @Value("${client.openWeatherMap.baseUrl}") String baseUrl,
            @Value("${client.openWeatherMap.apiKey}") String apiKey
    ) {
        return new OpenWeatherMapClient(restTemplate, baseUrl, apiKey);
    }

    @Bean
    public WeatherForecastProvider weatherForecastProvider(OpenWeatherMapClient openWeatherMapClient) {
        return new OpenWeatherMapForecastProvider(openWeatherMapClient);
    }

}
