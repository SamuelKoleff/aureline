package dev.skoleff.aureline.infrastructure.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductVariantResponse(
        UUID id,
        String sku,
        String size,
        String color,
        Integer stock,
        BigDecimal price
) {}

