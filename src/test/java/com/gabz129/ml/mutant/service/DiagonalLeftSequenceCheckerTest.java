package com.gabz129.ml.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DiagonalLeftSequenceCheckerTest {

    private DiagonalLeftSequenceChecker diagonalLeftSequenceChecker;
    private char[][] grid;

    @Before
    public void setUp(){
        final String[] data = {
                "AATAGAGAA",
                "CATAACGAC",
                "ATATCCACA",
                "GGTCGAAAC",
                "AGCCAXCAA",
                "TCTAAACAA",
                "CCAGGXATC",
                "GATTCXAAT",
                "ACTTCXAAA"
        };
        int sizeOfGrid = data.length;
        diagonalLeftSequenceChecker = new DiagonalLeftSequenceChecker(sizeOfGrid,4);

        grid = new char[sizeOfGrid][sizeOfGrid];
        int row = 0;
        for (String item : data) {
            grid[row] = item.toCharArray();
            row++;
        }
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 9 Characters
     */
    @Test
    public void shouldHaveADiagonalLeftSequenceOfNineCharacters() {
        int sizeOfSequence = diagonalLeftSequenceChecker.sizeOfSequence(grid,0,8,'A');
        assertThat(sizeOfSequence, is(9));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 6 Characters
     */
    @Test
    public void shouldHaveADiagonalLeftSequenceOfSix() {
        int sizeOfSequence = diagonalLeftSequenceChecker.sizeOfSequence(grid,1,5,'C');
        assertThat(sizeOfSequence, is(6));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found 2 sequences
     */
    @Test
    public void shouldReturnTwoSequences(){
        int numberOfSequence = diagonalLeftSequenceChecker.numberOfSequences(grid,0,8,'A');
        assertThat(numberOfSequence, is(2));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found 0 sequences
     */
    @Test
    public void shouldNotReturnASequence(){
        int numberOfSequence = diagonalLeftSequenceChecker.numberOfSequences(grid,0,3,'A');
        assertThat(numberOfSequence, is(0));
    }

}