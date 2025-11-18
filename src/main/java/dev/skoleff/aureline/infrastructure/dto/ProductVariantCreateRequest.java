package dev.skoleff.aureline.infrastructure.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductVariantCreateRequest(

        @NotBlank
        @Size(max = 50)
        String sku,

        @NotBlank
        @Size(max = 20)
        String size,

        @NotBlank
        @Size(max = 30)
        String color,

        @NotNull
        @Min(0)
        Integer stock,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @Digits(integer = 17, fraction = 2)
        BigDecimal price

) {}

