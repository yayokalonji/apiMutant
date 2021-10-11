package com.mutant.xmen.repository;

import com.mutant.xmen.models.Mutant;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends ReactiveCrudRepository<Mutant, String> {
}
