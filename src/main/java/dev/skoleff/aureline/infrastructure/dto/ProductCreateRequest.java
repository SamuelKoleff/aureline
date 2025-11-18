package dev.skoleff.aureline.infrastructure.dto;

import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreateRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 100)
        String model,

        @Size(max = 255)
        String description,

        @Size(max = 50)
        String brand,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 17, fraction = 2)
        BigDecimal price,

        @NotNull
        Gender gender,

        @NotNull
        ProductType type,

        @Valid
        @NotNull
        List<ProductVariantCreateRequest> productVariants

) {}
