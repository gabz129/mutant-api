package com.gabz129.ml.mutant.validator;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.message.Messages;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The type Dna validator.
 */
@Component
public class DnaValidator {

    private static final int MIN_SIZE = 4;
    /**
     * Validate if the request has valid parameters
     * Validations:
     * - Size of the Matrix NxN
     * - Characters should be one [A|T|G|C]
     *
     * @param dnaRequest the dna request
     */
    public void validate(final DnaRequest dnaRequest) {
        Objects.requireNonNull(dnaRequest, "Request should not be null");
        Objects.requireNonNull(dnaRequest.getDna(), "Request should not be null");

        int size = dnaRequest.getDna().length;
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException(Messages.ILLEGAL_ARGUMENT_SIZE.getMessage());
        }

        for (String row : dnaRequest.getDna()) {
            if (!row.matches("[ATCG]*")) {
                throw new IllegalArgumentException(Messages.ILLEGAL_ARGUMENT_CHARACTER.getMessage());
            }
            if (row.toCharArray().length != size) {
                throw new IllegalArgumentException(Messages.ILLEGAL_ARGUMENT_SIZE.getMessage());
            }
        }
    }
}
