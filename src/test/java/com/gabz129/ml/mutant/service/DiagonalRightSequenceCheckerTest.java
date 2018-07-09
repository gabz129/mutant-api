package com.gabz129.ml.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DiagonalRightSequenceCheckerTest {
    private DiagonalRightSequenceChecker diagonalRightSequenceChecker;
    private char[][] grid;

    @Before
    public void setUp(){
        final String[] data = {
                "AATAGACAA",
                "AATCACGAC",
                "ATATCCACA",
                "AGTAGCAAC",
                "AGTCAXCAA",
                "ACTTAAACA",
                "ACTGGXATC",
                "ACTTCXAAT",
                "ACTTCXAAA"
        };
        int sizeOfGrid = data.length;
        diagonalRightSequenceChecker = new DiagonalRightSequenceChecker(sizeOfGrid,4);

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
    public void shouldHaveADiagonalRightSequenceOfNineCharacters() {
        int sizeOfSequence = diagonalRightSequenceChecker.sizeOfSequence(grid,0,0,'A');
        assertThat(sizeOfSequence, is(9));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 6 Characters
     */
    @Test
    public void shouldHaveADiagonalRightSequenceOfSix() {
        int sizeOfSequence = diagonalRightSequenceChecker.sizeOfSequence(grid,1,3,'C');
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
        int numberOfSequence = diagonalRightSequenceChecker.numberOfSequences(grid,0,0,'A');
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
        int numberOfSequence = diagonalRightSequenceChecker.numberOfSequences(grid,0,2,'T');
        assertThat(numberOfSequence, is(0));
    }
}