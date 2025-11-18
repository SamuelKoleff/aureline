package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.repository.ProductRepository;

@UseCase
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Boolean execute(Product product){
        //TODO: delete images
        return productRepository.delete(product);
    }
}
