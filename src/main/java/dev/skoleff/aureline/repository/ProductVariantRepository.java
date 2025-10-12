package dev.skoleff.aureline.repository;


import dev.skoleff.aureline.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    List<ProductVariant> findByIsActiveTrueAndStockGreaterThan(Integer min_stock);
}
