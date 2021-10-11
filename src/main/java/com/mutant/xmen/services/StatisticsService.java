package com.mutant.xmen.services;

import com.mutant.xmen.models.Mutant;
import com.mutant.xmen.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class StatisticsService {

    @Autowired
    MutantRepository mutantRepository;

    public Flux<Mutant> getAllMutants() {
        return mutantRepository.findAll();
    }
}
