package com.ZooManagerApp.configuration;


import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@NoArgsConstructor
public class SwaggerConfiguration {

    @Bean
    public Docket getSwaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ZooManagerApp"))
                .build()
				.apiInfo(apiDetails());
    }

    private ApiInfo apiDetails () {
        return new ApiInfo(
                "Zoo Manager API",
                "An application that allows you to manage the duties of zoo employees.",
                "1.0",
                "Only for authorized users.",
                new springfox.documentation.service.Contact("Damian", "www.null.com", "damian.gorka94@gmail.com"),
                "API License",
                "www.null.com",
                Collections.emptyList());
    }
}
