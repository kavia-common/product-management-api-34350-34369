package com.example.productsapibackend.web;

import com.example.productsapibackend.dto.ProductDtos;
import com.example.productsapibackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller providing CRUD operations for products.
 */
@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "CRUD endpoints for managing products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List products", description = "Returns all products")
    @ApiResponse(responseCode = "200", description = "List of products",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProductDtos.ProductResponse.class))))
    public ResponseEntity<List<ProductDtos.ProductResponse>> getAll() {
        /** Returns all products. */
        return ResponseEntity.ok(service.getAll());
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get product by id", description = "Returns a single product by its id")
    @ApiResponse(responseCode = "200", description = "Product found",
            content = @Content(schema = @Schema(implementation = ProductDtos.ProductResponse.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductDtos.ProductResponse> getById(@PathVariable Long id) {
        /** Returns a product by id. */
        return ResponseEntity.ok(service.getById(id));
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create product", description = "Creates a new product")
    @ApiResponse(responseCode = "201", description = "Product created",
            content = @Content(schema = @Schema(implementation = ProductDtos.ProductResponse.class)))
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<ProductDtos.ProductResponse> create(@Valid @RequestBody ProductDtos.CreateProductRequest req) {
        /** Creates a product and returns the created resource with Location header. */
        ProductDtos.ProductResponse created = service.create(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id)
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Updates an existing product by id")
    @ApiResponse(responseCode = "200", description = "Product updated",
            content = @Content(schema = @Schema(implementation = ProductDtos.ProductResponse.class)))
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductDtos.ProductResponse> update(@PathVariable Long id,
                                                              @Valid @RequestBody ProductDtos.UpdateProductRequest req) {
        /** Updates a product by id. */
        return ResponseEntity.ok(service.update(id, req));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Deletes a product by id")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        /** Deletes a product by id. */
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
