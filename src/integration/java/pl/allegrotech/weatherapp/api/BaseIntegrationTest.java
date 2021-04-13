package pl.allegrotech.weatherapp.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.allegrotech.weatherapp.WeatherApplication;

@SpringBootTest(
        classes = WeatherApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class BaseIntegrationTest {

    @Value("${local.server.port}")
    protected int port;

    protected String localUrl(String endpoint) {
        return String.format("http://localhost:%s%s", port, endpoint);
    }

}
