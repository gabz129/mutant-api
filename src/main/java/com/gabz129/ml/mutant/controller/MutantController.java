package com.gabz129.ml.mutant.controller;

import com.gabz129.ml.mutant.controller.request.DnaRequest;
import com.gabz129.ml.mutant.controller.response.StatsResponse;
import com.gabz129.ml.mutant.message.Messages;
import com.gabz129.ml.mutant.service.AnalizerService;
import com.gabz129.ml.mutant.validator.DnaValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints to analize dna
 */
@RestController
@RequestMapping("/api")
@Api(value = "API Mutant", description = "Gets and analizes dna of possible mutants")
public class MutantController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantController.class);

    private AnalizerService analizerService;
    private DnaValidator dnaValidator;

    /**
     * Instantiates a new Mutant controller.
     *
     * @param analizerService the analizer service
     * @param dnaValidator    the dna validator
     */
    public MutantController(final AnalizerService analizerService,
                            final DnaValidator dnaValidator) {
        this.analizerService = analizerService;
        this.dnaValidator = dnaValidator;
    }

    /**
     * Analize dna and sent 200 OK if it is a mutant.
     * Otherwise, it will send 403 response
     *
     * @param dnaRequest {@link DnaRequest}
     * @return the response entity
     */
    @ApiOperation(value = "Receive a list of DNAs and checks if its a mutant", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "DNA is a mutant"),
            @ApiResponse(code = 403, message = "DNA is not a mutant")})
    @RequestMapping(value = "/mutant",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity analizeDna(@RequestBody final DnaRequest dnaRequest) {
        dnaValidator.validate(dnaRequest);

        LOGGER.debug("Analizing dna");
        analizerService.check(dnaRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Messages.SUCCESS_RESULT.getMessage());
    }

    /**
     * Report dna response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "Generate a report of stats", response = StatsResponse.class)
    @RequestMapping(value = "/stats",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reportDna() {
        LOGGER.debug("Generating report");
        return ResponseEntity.status(HttpStatus.OK)
                .body(analizerService.generateReport());
    }
}
