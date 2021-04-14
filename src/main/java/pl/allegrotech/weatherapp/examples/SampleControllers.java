package pl.allegrotech.weatherapp.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class SampleControllers {
    private static final String CONTENT_TYPE_V1 = "application/v1+json";
    private static final String CONTENT_TYPE_V2 = "application/v2+json";

    private static final Logger logger = LoggerFactory.getLogger(SampleControllers.class);

    @GetMapping(path = "/getController", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SampleResponse> getDetails(@RequestParam(name = "name") String name, @RequestParam(name = "age") int age) {
        logger.info("Getting new person name={}, age={}", name, age);
        return ResponseEntity.ok(new SampleResponse(name, age));
    }

    @PostMapping(path = "/postController", consumes = APPLICATION_JSON_VALUE, produces = CONTENT_TYPE_V1)
    ResponseEntity<SampleResponse> postDetails(@RequestBody SampleRequest request) {
        logger.info("Getting new person name={}, birthYear={}", request.getName(), request.getBirthYear());
        SampleResponse response = new SampleResponse(request.getName(), 2021 - request.getBirthYear());
        return ResponseEntity
                .status(201)
                .body(response);
    }

    @PostMapping(path = "/postController", consumes = APPLICATION_JSON_VALUE, produces = CONTENT_TYPE_V2)
    ResponseEntity<SampleResponse> postDetailsV2(@RequestBody SampleRequest request) {
        logger.info("Getting new person name={}, birthYear={}", request.getName(), request.getBirthYear());
        SampleResponse response = new SampleResponse(request.getName(), 2022 - request.getBirthYear());
        return ResponseEntity
                .status(201)
                .body(response);
    }

    @PostMapping(path = "/postControllerWithHeader", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<SampleResponse> postDetailsWithLocation(@RequestBody SampleRequest request, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("Getting new person name={}, birthYear={}", request.getName(), request.getBirthYear());
        SampleResponse response = new SampleResponse(request.getName(), 2021 - request.getBirthYear());
        UriComponents uriComponents = uriComponentsBuilder
                .path("/postControllerWithHeader")
                .queryParam("param", "value")
                .build();
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(response);
    }
}
