package com.mutant.xmen.router;

import com.mutant.xmen.handler.MutantHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class MutantRouter {

    @Bean
    public RouterFunction<ServerResponse> mutantsRouter(MutantHandler mutantHandler) {
        return RouterFunctions.route(POST("/mutant").and(accept(MediaType.APPLICATION_JSON)), mutantHandler::mutant);
    }
}
