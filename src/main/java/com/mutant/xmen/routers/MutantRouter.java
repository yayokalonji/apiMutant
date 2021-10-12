package com.mutant.xmen.routers;

import com.mutant.xmen.handlers.MutantHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MutantRouter {

    @Bean
    public RouterFunction<ServerResponse> mutantsRouter(MutantHandler mutantHandler) {
        return RouterFunctions.route(POST("/mutant").and(accept(MediaType.APPLICATION_JSON)), mutantHandler::mutant)
                .andRoute(GET("/stats").and(accept(MediaType.APPLICATION_JSON)), mutantHandler::getAllMutants);
    }
}
