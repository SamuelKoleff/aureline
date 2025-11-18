package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.repository.ProductRepository;

import java.util.List;

@UseCase
public class GetActiveProductsUseCase {
    private ProductRepository productRepository;

    public GetActiveProductsUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> execute(){
        return productRepository.getAllActives();
    }
}
