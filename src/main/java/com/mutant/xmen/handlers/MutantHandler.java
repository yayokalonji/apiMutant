package com.mutant.xmen.handlers;

import com.mutant.xmen.models.Mutant;
import com.mutant.xmen.models.RequestDTO;
import com.mutant.xmen.repository.MutantRepository;
import com.mutant.xmen.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class MutantHandler {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private MutantRepository mutantRepository;

    public Mono<ServerResponse> mutant(ServerRequest request) {
        Mono<RequestDTO> requestDTOs = request.bodyToMono(RequestDTO.class);
        return requestDTOs.flatMap(p -> {
            if (this.validate(p) || this.validateLength(p)) {
                return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).bodyValue("Verify the information.........");
            }
            boolean isMutant = mutantService.isMutant(p);
            this.save(isMutant);
            return isMutant ? ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(true) :
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

    private boolean validateLength(RequestDTO requestDTO) {
        AtomicBoolean result = new AtomicBoolean(false);
        requestDTO.getDna().forEach(x -> {
            if (requestDTO.getDna().size() != x.length()) {
                result.set(true);
            }
        });
        return result.get();
    }

    public Mono<ServerResponse> getAllMutants(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mutantRepository.findAll().flatMap(x -> {
                    Mutant mutant = new Mutant();
                    mutant.setCountHumanDna(x.getCountHumanDna());
                    mutant.setCountMutantDna(x.getCountMutantDna());
                    mutant.setRatio(x.getRatio());
                    return Flux.just(mutant);
                }).last(), Mutant.class);
    }

    private void save(boolean isMutant) {
        Flux<Mutant> mutantFlux = mutantRepository.findAll();
        mutantFlux.flatMap(b -> {
            if (isMutant) {
                b.setCountMutantDna(b.getCountMutantDna() + 1);
            } else {
                b.setCountHumanDna(b.getCountHumanDna() + 1);
            }
            b.setRatio(b.getCountMutantDna() / (b.getCountHumanDna() == 0 ? 1 : b.getCountHumanDna()));
            mutantRepository.save(b).subscribe();
            return Flux.just(b);
        }).subscribe();
    }
}
