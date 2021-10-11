package com.mutant.xmen.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "mutant")
@Data
public class Mutant {

    @Id
    private ObjectId id;
    @Field("count_mutant_dna")
    private double countMutantDna;
    @Field("count_human_dna")
    private double countHumanDna;
}
