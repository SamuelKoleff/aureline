package dev.skoleff.aureline.infrastructure.persistence.repository;

import dev.skoleff.aureline.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByActiveTrue();

    @Override
    Optional<ProductEntity> findById(UUID uuid);
}
