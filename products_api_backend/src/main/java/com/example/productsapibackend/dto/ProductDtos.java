package com.example.productsapibackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * DTO classes for Product API to decouple persistence model from API contracts.
 */
public class ProductDtos {

    @Schema(name = "CreateProductRequest", description = "Payload to create a product")
    public static class CreateProductRequest {
        @NotBlank
        @Schema(description = "Product name", example = "Wireless Mouse", requiredMode = Schema.RequiredMode.REQUIRED)
        public String name;

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @Schema(description = "Product price", example = "19.99", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
        public BigDecimal price;

        @NotNull
        @PositiveOrZero
        @Schema(description = "Product quantity", example = "50", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
        public Integer quantity;
    }

    @Schema(name = "UpdateProductRequest", description = "Payload to update a product")
    public static class UpdateProductRequest {
        @NotBlank
        @Schema(description = "Product name", example = "Wireless Mouse Pro", requiredMode = Schema.RequiredMode.REQUIRED)
        public String name;

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @Schema(description = "Product price", example = "29.99", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
        public BigDecimal price;

        @NotNull
        @PositiveOrZero
        @Schema(description = "Product quantity", example = "75", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
        public Integer quantity;
    }

    @Schema(name = "ProductResponse", description = "Representation of a product")
    public static class ProductResponse {
        @Schema(description = "Product id", example = "1")
        public Long id;

        @Schema(description = "Product name", example = "Wireless Mouse")
        public String name;

        @Schema(description = "Product price", example = "19.99")
        public BigDecimal price;

        @Schema(description = "Product quantity", example = "50")
        public Integer quantity;

        public ProductResponse() {}

        public ProductResponse(Long id, String name, BigDecimal price, Integer quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }
}
