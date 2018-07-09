package com.gabz129.ml.mutant.service;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.controller.response.StatsResponse;
import com.gabz129.ml.mutant.entity.DnaResult;
import com.gabz129.ml.mutant.exception.DnaMutantException;
import com.gabz129.ml.mutant.message.Messages;
import com.gabz129.ml.mutant.repository.DnaResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

/**
 * Service to check dna and generate report
 */
@Service
public class AnalizerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalizerService.class);

    private DnaResultRepository dnaResultRepository;
    private ConcurrentHashMap<String, Boolean> resultsCache = new ConcurrentHashMap<>();

    private static final int MIN_SEQ_CHARACTERS = 4;
    private static final int NON_RESULTS = 0;
    private static final int NO_SEQ_REQUIRED = 2;

    /**
     * Instantiates a new Analizer service.
     *
     * @param dnaResultRepository the dna result repository
     */
    public AnalizerService(final DnaResultRepository dnaResultRepository) {
        this.dnaResultRepository = dnaResultRepository;
    }

    /**
     * Checks the requested dna and saves result
     *
     * @param request the request
     */
    public void check(final DnaRequest request) {
        Objects.requireNonNull(request, "Request should not be null");
        boolean result = false;
        String formattedDna = Arrays.toString(request.getDna());

        if (resultsCache.containsKey(formattedDna)) {
            LOGGER.debug("Found in cache");
            result = resultsCache.get(formattedDna);
        } else {
            result = isMutant(request.getDna());
            resultsCache.put(formattedDna, result);

            LOGGER.debug("Saving result");
            Long idResult = (long) formattedDna.hashCode();
            DnaResult dnaResult = new DnaResult(idResult, formattedDna, result);
            dnaResultRepository.save(dnaResult);
            LOGGER.info("Checked and got result for: {}", formattedDna);
        }

        if (!result) {
            LOGGER.debug("Dna is not a mutant");
            throw new DnaMutantException(Messages.ERROR_NOT_MUTANT.getMessage());
        }
    }

    /**
     * Generate report using all data from database
     *
     * @return the stats response
     */
    public StatsResponse generateReport() {
        LOGGER.debug("Fetching data");
        List<DnaResult> retrievedResults = (List<DnaResult>) dnaResultRepository.findAll();
        int totalResults = retrievedResults.size();

        LOGGER.debug("Generating report");
        if (totalResults == 0) {
            return new StatsResponse(NON_RESULTS, NON_RESULTS, BigDecimal.ZERO);
        }
        int totalMutants = retrievedResults.stream()
                .filter(DnaResult::isMutant)
                .collect(toList())
                .size();
        return new StatsResponse(totalMutants,
                totalResults - totalMutants,
                BigDecimal.valueOf((float) totalMutants / (float) totalResults));
    }

    /**
     * Check if the dna is a mutant by counting sequences
     *
     * @param dna {@link String[]}
     * @return if it is a mutant
     */
    //TODO: this should be private
    public boolean isMutant(final String[] dna) {
        Objects.requireNonNull(dna, "Dna should not be null");
        //Convert to matrix
        int size = dna.length;
        char[][] dnaMatrix = convertToMatrix(dna, size);

        int sequenceCounter = 0;
        List<SequenceChecker> sequenceCheckers = getSequenceCheckers(size);

        //Check each value of the matrix
        for (int rowPos = 0; rowPos < size; rowPos++) {
            for (int columnPos = 0; columnPos < size; columnPos++) {
                char currentCharacter = dnaMatrix[rowPos][columnPos];

                //Search sequences for each type
                for (SequenceChecker sequenceChecker : sequenceCheckers) {
                    sequenceCounter += sequenceChecker.numberOfSequences(dnaMatrix, rowPos, columnPos, currentCharacter);
                    if (sequenceCounter >= NO_SEQ_REQUIRED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Convert an Array of String into Matrix of Char
     *
     * @param data {@link String[]}
     * @param size of the data
     * @return a matrix of char
     */
    private char[][] convertToMatrix(final String[] data, final int size) {
        char[][] matrix = new char[size][size];
        int row = 0;
        for (String item : data) {
            matrix[row] = item.toCharArray();
            row++;
        }
        return matrix;
    }

    /**
     * Initialize sequence checkers
     *
     * @param size size of the matrix
     * @return a list of sequence checkers
     */
    private List<SequenceChecker> getSequenceCheckers(final int size) {
        return Arrays.asList(
                new HorizontalSequenceChecker(size, MIN_SEQ_CHARACTERS),
                new VerticalSequenceChecker(size, MIN_SEQ_CHARACTERS),
                new DiagonalRightSequenceChecker(size, MIN_SEQ_CHARACTERS),
                new DiagonalLeftSequenceChecker(size, MIN_SEQ_CHARACTERS));
    }
}
