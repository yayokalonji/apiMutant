package com.mutant.xmen.repository;

import com.mutant.xmen.models.Mutant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends ReactiveMongoRepository<Mutant, String> {
}
