package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.repository.ProductRepository;

@UseCase
public class SaveProductUseCase {
    private ProductRepository productRepository;

    public SaveProductUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product execute(Product product){
        Product saved = productRepository.save(product);
        return saved;
    }
}
