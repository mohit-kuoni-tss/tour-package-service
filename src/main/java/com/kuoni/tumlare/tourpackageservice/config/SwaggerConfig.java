package com.kuoni.tumlare.tourpackageservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI Configuration for the Tour Package Service.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI documentation for the project.
     * 
     * @return the OpenAPI object with project details
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tour & Travel Management System API")
                        .version("1.0")
                        .description("Reactive REST APIs for managing tour packages using Spring WebFlux and Oracle R2DBC.")
                        .contact(new Contact()
                                .name("Development Team")
                                .email("dev-team@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}

