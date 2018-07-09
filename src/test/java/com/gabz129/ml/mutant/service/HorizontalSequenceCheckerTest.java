package com.gabz129.ml.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HorizontalSequenceCheckerTest {

    private HorizontalSequenceChecker horizontalSequenceChecker;
    private char[][] grid;

    @Before
    public void setUp(){
        final String[] data = {
                "AAAAAAAAA",
                "CACAACGAA",
                "TTTTTTACA",
                "AGAAGGAAC",
                "CGCCAAAAA",
                "TCTTATAAA",
                "TCTGGGGTC",
                "TCTTCTAAT",
                "TCTTCTAAA"
        };
        int sizeOfGrid = data.length;
        horizontalSequenceChecker = new HorizontalSequenceChecker(sizeOfGrid,4);

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
    public void shouldHaveAHorizontalSequenceOfEightCharacters() {
        int sizeOfSequence = horizontalSequenceChecker.sizeOfSequence(grid,0,0,'A');
        assertThat(sizeOfSequence, is(9));
    }

    /**
     * Scenario:
     *  Check grid and find a sequence
     * Expectation:
     *  Found a sequence of 6 Characters
     */
    @Test
    public void shouldHaveAHorizontalSequenceOfSix() {
        int sizeOfSequence = horizontalSequenceChecker.sizeOfSequence(grid,2,0,'T');
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
        int numberOfSequence = horizontalSequenceChecker.numberOfSequences(grid,0,0,'A');
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
        int numberOfSequence = horizontalSequenceChecker.numberOfSequences(grid,3,0,'A');
        assertThat(numberOfSequence, is(0));
    }

}