package com.gabz129.ml.mutant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * The type Dna result.
 */
@Entity
public class DnaResult {

    @Id
    private Long id;
    @NotNull
    private String dna;
    @NotNull
    private boolean result;

    /**
     * Instantiates a new Dna result.
     */
    public DnaResult() {
    }

    /**
     * Instantiates a new Dna result.
     *
     * @param id     the id
     * @param dna    the dna
     * @param result the result
     */
    public DnaResult(final Long id, final String dna, final boolean result) {
        this.id = id;
        this.dna = dna;
        this.result = result;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets dna.
     *
     * @return the dna
     */
    public String getDna() {
        return dna;
    }

    /**
     * Is mutant boolean.
     *
     * @return the boolean
     */
    public boolean isMutant() {
        return result;
    }
}
