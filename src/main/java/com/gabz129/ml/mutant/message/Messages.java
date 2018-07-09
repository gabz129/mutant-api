package com.gabz129.ml.mutant.message;

/**
 * The enum Messages.
 */
public enum Messages {
    SUCCESS_RESULT("Analizing sucessfully"),
    ERROR_NOT_MUTANT("This is not a mutant"),
    ILLEGAL_ARGUMENT_CHARACTER("Characters should be [A|T|G|C]"),
    ILLEGAL_ARGUMENT_SIZE("Size of the matrix should be NxN, N should be equal or greater than 4");

    private final String message;

    /**
     * Constructor
     *
     * @param message the message
     */
    Messages(final String message) {
        this.message = message;
    }

    /**
     * Get message string.
     *
     * @return the string
     */
    public String getMessage() {
        return message;
    }
}
