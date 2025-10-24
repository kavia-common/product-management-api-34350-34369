package com.example.productsapibackend.config;

import com.example.productsapibackend.model.Product;
import com.example.productsapibackend.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * Seeds initial data into the in-memory H2 database for quick testing.
 */
@Configuration
@Tag(name = "Seed Data", description = "Initial data loader for in-memory DB")
public class SeedDataConfig {

    private static final Logger log = LoggerFactory.getLogger(SeedDataConfig.class);

    @Bean
    CommandLineRunner seedProducts(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Product("Wireless Mouse", new BigDecimal("19.99"), 50));
                repo.save(new Product("Mechanical Keyboard", new BigDecimal("89.90"), 20));
                repo.save(new Product("USB-C Cable", new BigDecimal("7.49"), 150));
                log.info("Seeded initial products ({} entries)", repo.count());
            }
        };
    }
}
