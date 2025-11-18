package dev.skoleff.aureline.infrastructure.dto;

import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String model,
        String description,
        String brand,
        BigDecimal price,
        Boolean active,
        Gender gender,
        ProductType type,
        List<ProductVariantResponse> productVariants
) {}
