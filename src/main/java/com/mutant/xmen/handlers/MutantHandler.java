package com.mutant.xmen.handlers;

import com.mutant.xmen.models.RequestDTO;
import com.mutant.xmen.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class MutantHandler {

    @Autowired
    private MutantService mutantService;

    public Mono<ServerResponse> mutant(ServerRequest request) {
        Mono<RequestDTO> requestDTOs = request.bodyToMono(RequestDTO.class);
        return requestDTOs.flatMap(p -> {
            if (this.validate(p)) {
                return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).bodyValue("Only the letters A, C, G and T");
            }
            return mutantService.isMutant(p) ? ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(true) :
                    ServerResponse.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).bodyValue(false);
        });
    }

    private boolean validate(RequestDTO requestDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        requestDTO.getDna().forEach(x -> {
            Pattern pattern = Pattern.compile("[acgt]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(x);
            if (!matcher.matches()) {
                result.set(true);
            }
        });
        return result.get();
    }
}
