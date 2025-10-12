package dev.skoleff.aureline.service;

import dev.skoleff.aureline.model.ProductVariant;
import dev.skoleff.aureline.repository.ProductVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    public ProductVariantService(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }

    public List<ProductVariant> getAllVariants() {
        return productVariantRepository.findAll();
    }


    public List<ProductVariant> getAllVariantsActivesAndExist() {
        return productVariantRepository.findByIsActiveTrueAndStockGreaterThan(0);
    }


    public Optional<ProductVariant> getVariantById(Long id) {
        return productVariantRepository.findById(id);
    }

    public ProductVariant saveVariant(ProductVariant variant) {
        return productVariantRepository.save(variant);
    }

    public void deleteVariant(Long id) {
        productVariantRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productVariantRepository.existsById(id);
    }
}
