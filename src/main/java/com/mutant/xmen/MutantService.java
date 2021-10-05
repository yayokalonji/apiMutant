package com.mutant.xmen;

import com.mutant.xmen.model.RequestDTO;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private static final String A = "AAAA";
    private static final String T = "TTTT";
    private static final String C = "CCCC";
    private static final String G = "GGGG";

    public boolean isMutant(RequestDTO requestDTO) {
        return requestDTO.getDna().stream().anyMatch(s -> s.contains(A) || s.contains(T) || s.contains(C) || s.contains(G));
    }
}
