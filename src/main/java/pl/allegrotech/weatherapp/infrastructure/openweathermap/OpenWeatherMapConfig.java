package pl.allegrotech.weatherapp.infrastructure.openweathermap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.allegrotech.weatherapp.domain.WeatherForecastProvider;
import pl.allegrotech.weatherapp.infrastructure.openweathermap.client.OpenWeatherMapClient;

@Configuration
class OpenWeatherMapConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        // TODO Zadanie 3
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(1000);
        factory.setConnectionRequestTimeout(1000);
        factory.setReadTimeout(1000);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpRequestFactory) {
        // TODO Zadanie 3
        return new RestTemplate(httpRequestFactory);
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
