package com.kuoni.tumlare.tourpackageservice.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

/**
 * R2DBC Configuration for the Tour Package Service.
 * Enables R2DBC repositories and auditing (@CreatedDate, @LastModifiedDate).
 * Also configures automatic schema initialization.
 * 
 * @author Mohit Kumar
 * @version 1.0
 */
@Configuration
@EnableR2dbcRepositories(basePackages = "com.kuoni.tumlare.tourpackageservice.repository")
@EnableR2dbcAuditing
public class R2dbcConfig {

    /**
     * Initializes the database schema using schema.sql on startup.
     * Note: In production, consider using a proper migration tool like Flyway or Liquibase.
     * For R2DBC, this helps in creating tables if they don't exist.
     *
     * @param connectionFactory the R2DBC connection factory
     * @return ConnectionFactoryInitializer
     */
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
        populator.setSeparator("/");
        // Setting continueOnError to true so it doesn't fail if table already exists
        populator.setContinueOnError(false);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}

