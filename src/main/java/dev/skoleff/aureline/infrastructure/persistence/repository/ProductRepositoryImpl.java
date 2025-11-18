package dev.skoleff.aureline.infrastructure.persistence.repository;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.repository.ProductRepository;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductEntity;
import dev.skoleff.aureline.infrastructure.persistence.entity.mapper.ProductEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository repository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryImpl(JpaProductRepository repository, ProductEntityMapper productEntityMapper) {
        this.repository = repository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Product findById(UUID productId) {
        return repository.findById(productId)
                .map(productEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Boolean existsById(UUID productId) {
        return repository.existsById(productId);
    }

    @Override
    public Boolean delete(Product product) {
        try {
            repository.delete(productEntityMapper.toEntity(product));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = productEntityMapper.toEntity(product);
        ProductEntity saved = repository.save(entity);
        return productEntityMapper.toDomain(saved);
    }

    @Override
    public List<Product> getAllActives() {
        return repository.findByActiveTrue()
                .stream()
                .map(productEntityMapper::toDomain)
                .collect(Collectors.toList());
    }


}
