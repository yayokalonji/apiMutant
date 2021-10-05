package com.mutant.xmen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

public class MutantController {

    public Mono<String> getResponseExample(){
        return Mono.just("Hello world !");
    }
}
