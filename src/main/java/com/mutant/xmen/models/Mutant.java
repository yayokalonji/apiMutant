package com.mutant.xmen.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "mutant")
@Data
public class Mutant {

    @Id
    @JsonIgnore
    private Object id;
    @Field("count_mutant_dna")
    private double countMutantDna;
    @Field("count_human_dna")
    private double countHumanDna;
    private double ratio;
}
