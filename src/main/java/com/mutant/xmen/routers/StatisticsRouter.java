package com.mutant.xmen.routers;

import com.mutant.xmen.handlers.StatisticsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class StatisticsRouter {

    @Bean
    public RouterFunction<ServerResponse> statictsRouter(StatisticsHandler statisticsHandler) {
        return RouterFunctions.route(GET("/stats").and(accept(MediaType.APPLICATION_JSON)), statisticsHandler::getAllMutants);
    }
}
