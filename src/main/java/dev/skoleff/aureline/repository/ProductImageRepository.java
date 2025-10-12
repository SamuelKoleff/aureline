package dev.skoleff.aureline.repository;

import dev.skoleff.aureline.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
