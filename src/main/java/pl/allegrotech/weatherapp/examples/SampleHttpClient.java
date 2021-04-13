package pl.allegrotech.weatherapp.examples;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.allegrotech.weatherapp.examples.SampleHttpClientResponse.Book;

import java.util.stream.Collectors;

@RestController
class SampleHttpClient {

    private final RestTemplate restTemplate;

    SampleHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/sample-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SampleHttpClientResponse> execute() {
        ResponseEntity<SampleHttpClientResponse> response = callExternalService();
        printResponse(response);
        return ResponseEntity.ok(response.getBody());
    }

    private ResponseEntity<SampleHttpClientResponse> callExternalService() {
        return restTemplate.getForEntity("https://run.mocky.io/v3/f910bf4a-80c0-461b-93d7-98abad5f01b9", SampleHttpClientResponse.class);
    }

    private void printResponse(ResponseEntity<SampleHttpClientResponse> response) {
        SampleHttpClientResponse user = response.getBody();
        String titles = user.getBooks().stream().map(Book::getTitle).collect(Collectors.joining(", "));
        System.out.printf("User with id = %s has books: %s%n", user.getUserName(), titles);
    }

}
