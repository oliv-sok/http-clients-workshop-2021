package pl.allegrotech.weatherapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import pl.allegrotech.weatherapp.WeatherApplication;

@SpringBootTest(
        classes = WeatherApplication.class,
        properties = "application.environment=integration",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class BaseIntegrationTest {

    @Value("${local.server.port}")
    protected int port;

    protected String localUrl(String endpoint) {
        return String.format("http://localhost:%s%s", port, endpoint);
    }

}
