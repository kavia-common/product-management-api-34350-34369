package com.example.productsapibackend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Product entity representing items with name, price, and quantity.
 */
@Entity
@Table(name = "products")
@Schema(name = "Product", description = "Represents a product with pricing and stock quantity")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    @Schema(description = "Name of the product", example = "Wireless Mouse", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be >= 0")
    @Column(nullable = false, precision = 15, scale = 2)
    @Schema(description = "Price of the product", example = "19.99", minimum = "0")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be >= 0")
    @Column(nullable = false)
    @Schema(description = "Available stock quantity", example = "100", minimum = "0")
    private Integer quantity;

    public Product() {}

    public Product(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // PUBLIC_INTERFACE
    public Long getId() {
        /** Returns the unique identifier of the product. */
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // PUBLIC_INTERFACE
    public String getName() {
        /** Returns the product name. */
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // PUBLIC_INTERFACE
    public BigDecimal getPrice() {
        /** Returns the product price. */
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // PUBLIC_INTERFACE
    public Integer getQuantity() {
        /** Returns the product quantity in stock. */
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
