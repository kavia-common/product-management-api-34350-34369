package com.example.productsapibackend.service;

import com.example.productsapibackend.dto.ProductDtos;
import com.example.productsapibackend.model.Product;
import com.example.productsapibackend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing product operations.
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public List<ProductDtos.ProductResponse> getAll() {
        /** Returns all products as response DTOs. */
        return repository.findAll().stream()
                .map(p -> new ProductDtos.ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getQuantity()))
                .toList();
    }

    // PUBLIC_INTERFACE
    public ProductDtos.ProductResponse getById(Long id) {
        /** Returns a product by id or throws NotFoundException. */
        Product p = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductDtos.ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getQuantity());
    }

    // PUBLIC_INTERFACE
    public ProductDtos.ProductResponse create(ProductDtos.CreateProductRequest req) {
        /** Creates a product from the provided request. */
        Product p = new Product(req.name, req.price, req.quantity);
        Product saved = repository.save(p);
        return new ProductDtos.ProductResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getQuantity());
    }

    // PUBLIC_INTERFACE
    public ProductDtos.ProductResponse update(Long id, ProductDtos.UpdateProductRequest req) {
        /** Updates an existing product with new data or throws NotFoundException. */
        Product p = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        p.setName(req.name);
        p.setPrice(req.price);
        p.setQuantity(req.quantity);
        Product saved = repository.save(p);
        return new ProductDtos.ProductResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getQuantity());
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        /** Deletes a product by id, throwing NotFoundException if it doesn't exist. */
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }

    /** Custom not found exception for products. */
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(Long id) {
            super("Product with id " + id + " not found");
        }
    }
}
