package com.gabz129.ml.mutant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Configuration of API Documentation using Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gabz129.ml.mutant.controller"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * Meta data of API Documentation.
     *
     * @return {@link ApiInfo}
     */
    private ApiInfo metaData() {
        Contact contact = new Contact("Gabriel Lopez Condori", null, null);
        return new ApiInfo(
                "X-Men API Rest",
                "API Rest to check dna of mutants",
                "1.0",
                null,
                contact,
                null,
                null,
                new ArrayList<>());
    }
}
