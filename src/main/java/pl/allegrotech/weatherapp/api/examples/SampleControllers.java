package pl.allegrotech.weatherapp.api.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class SampleControllers {

    private static final Logger logger = LoggerFactory.getLogger(SampleControllers.class);

    @GetMapping(path = "/getController", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SampleResponse> getDetails(@RequestParam(name = "name") String name, @RequestParam(name = "age") int age) {
        logger.info("Getting new person name={}, age={}", name, age);
        return ResponseEntity.ok(new SampleResponse(name, age));
    }

    @PostMapping(path = "/postController", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SampleResponse> postDetails(@RequestBody SampleRequest request) {
        logger.info("Getting new person name={}, birthYear={}", request.getName(), request.getBirthYear());
        SampleResponse response = new SampleResponse(request.getName(), 2021 - request.getBirthYear());
        return ResponseEntity
                .status(201)
                .body(response);
    }
}
