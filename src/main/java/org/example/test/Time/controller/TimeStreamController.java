package org.example.test.Time.controller;

import org.example.test.domain.Problem;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class TimeStreamController {

    @GetMapping(value = "/time-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> timeStream() {
        Problem problem = new Problem();

        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> "Time now is: " + LocalDateTime.now().toString());
    }
}
