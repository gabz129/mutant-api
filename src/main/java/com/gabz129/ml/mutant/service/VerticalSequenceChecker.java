package com.gabz129.ml.mutant.service;

/**
 * The type Vertical sequence checker.
 */
public class VerticalSequenceChecker implements SequenceChecker {

    private boolean[][] checked;
    private int minSeqCharacters;

    /**
     * Instantiates a new Vertical sequence checker.
     *
     * @param size          the size
     * @param minSeqCharacter the min seq characters
     */
    public VerticalSequenceChecker(final int size, final int minSeqCharacter) {
        this.checked = new boolean[size][size];
        this.minSeqCharacters = minSeqCharacter;
    }

    @Override
    public int sizeOfSequence(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        //If the item was checked, then avoid it
        if (rowPos < grid.length && checked[rowPos][columnPos]) {
            return 0;
        }

        if (rowPos < grid.length && grid[rowPos][columnPos] == character) {
            checked[rowPos][columnPos] = true;
            return 1 + sizeOfSequence(grid, rowPos + 1, columnPos, character);
        }
        return 0;
    }

    @Override
    public int numberOfSequences(final char[][] grid, final int rowPos, final int columnPos, final char character) {
        return sizeOfSequence(grid, rowPos, columnPos, character) / minSeqCharacters;
    }
}
