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
        if (searchVertical(requestDTO)) {
            return true;
        } else {
            return searchHorizontal(requestDTO);
        }
    }

    private boolean searchHorizontal(RequestDTO requestDTO) {
        return requestDTO.getDna().stream().anyMatch(
                s -> s.toUpperCase().contains(A) || s.toUpperCase().contains(T) || s.toUpperCase().contains(C) || s.toUpperCase().contains(G));
    }

    private boolean searchVertical(RequestDTO requestDTO) {
        StringBuilder chain = new StringBuilder();
        boolean result = false;
        for (int i = 0; i < requestDTO.getDna().size(); i++) {
            for (int j = 0; j < requestDTO.getDna().size(); j++) {
                chain.append(requestDTO.getDna().get(j).charAt(i));
            }
            result = chain.toString().toUpperCase().contains(A) || chain.toString().toUpperCase().contains(T)
                    || chain.toString().toUpperCase().contains(C) || chain.toString().toUpperCase().contains(G);
            chain.setLength(0);
            if (result) {
                break;
            }
        }
        return result;
    }
}
