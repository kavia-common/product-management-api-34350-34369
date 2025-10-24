package com.example.productsapibackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the Products API Backend.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Products API",
                version = "0.1.0",
                description = "CRUD API for managing products (Ocean Professional style)",
                contact = @Contact(name = "API Support", email = "support@example.com")
        )
)
public class productsapibackendApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /** Boots the Spring application. */
        SpringApplication.run(productsapibackendApplication.class, args);
    }
}
