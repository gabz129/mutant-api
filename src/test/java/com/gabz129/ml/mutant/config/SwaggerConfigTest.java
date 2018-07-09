package com.gabz129.ml.mutant.config;

import org.junit.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class SwaggerConfigTest {

    /**
     * Scenario:
     *  Check swagger configuration
     * Expectation:
     *  Instance is created
     */
    @Test
    public void shouldSetSwaggerConfiguration(){
        SwaggerConfig config = new SwaggerConfig();
        assertThat(config, is(notNullValue()));

        Docket apiConfig = config.api();
        assertThat(apiConfig, is(notNullValue()));
    }
}