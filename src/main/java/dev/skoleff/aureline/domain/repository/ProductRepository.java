package dev.skoleff.aureline.domain.repository;

import dev.skoleff.aureline.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product findById(UUID productId);
    Boolean existsById(UUID productId);
    Product save(Product product);
    Boolean delete(Product product);
    List<Product> getAllActives();
}
