package com.mutant.xmen.handler;

import com.mutant.xmen.MutantService;
import com.mutant.xmen.model.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class MutantHandler {

    @Autowired
    private MutantService mutantService;

    public Mono<ServerResponse> mutant(ServerRequest request) {
        Mono<RequestDTO> requestDTOs = request.bodyToMono(RequestDTO.class);
        return requestDTOs.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(mutantService.isMutant(p)));
    }
}
