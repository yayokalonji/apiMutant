package com.mutant.xmen.router;

import com.mutant.xmen.handler.MutantHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ConfigurationRouter {

    @Bean
    public RouterFunction<ServerResponse> routeExample(MutantHandler mutantHandler){
        return RouterFunctions.route(RequestPredicates.GET("/example").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), mutantHandler::hello);
    }
}
