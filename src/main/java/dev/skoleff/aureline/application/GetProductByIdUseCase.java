package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.repository.ProductRepository;

import java.util.UUID;

@UseCase
public class GetProductByIdUseCase {
    private ProductRepository productRepository;

    public GetProductByIdUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product execute(UUID productId){
        return productRepository.findById(productId);
    }
}
