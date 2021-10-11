package com.mutant.xmen.handlers;

import com.mutant.xmen.models.Mutant;
import com.mutant.xmen.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StatisticsHandler {

    @Autowired
    StatisticsService statisticsServices;

    public Mono<ServerResponse> getAllMutants(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(statisticsServices.getAllMutants().collectList(), Mutant.class);
    }
}
