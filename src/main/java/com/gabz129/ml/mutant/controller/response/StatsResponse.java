package com.gabz129.ml.mutant.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response for stats
 */
public class StatsResponse {

    @JsonProperty("count_mutant_dna")
    private int mutantCounter;
    @JsonProperty("count_human_dna")
    private int humanCounter;
    @JsonProperty("ratio")
    private BigDecimal ratio;

    /**
     * Instantiates a new Stats response.
     */
    public StatsResponse() {
    }

    /**
     * Instantiates a new Stats response.
     *
     * @param mutantCounter the mutant counter
     * @param humanCounter  the human counter
     * @param ratio         the ratio
     */
    public StatsResponse(final int mutantCounter,
                         final int humanCounter,
                         final BigDecimal ratio) {
        this.mutantCounter = mutantCounter;
        this.humanCounter = humanCounter;
        this.ratio = ratio.setScale(1, BigDecimal.ROUND_CEILING);
    }

    /**
     * Gets mutant counter.
     *
     * @return the mutant counter
     */
    public int getMutantCounter() {
        return mutantCounter;
    }

    /**
     * Gets human counter.
     *
     * @return the human counter
     */
    public int getHumanCounter() {
        return humanCounter;
    }

    /**
     * Gets ratio.
     *
     * @return the ratio
     */
    public BigDecimal getRatio() {
        return ratio;
    }
}
