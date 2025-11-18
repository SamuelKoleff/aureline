package dev.skoleff.aureline.domain.model;

import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

public record Product(

        UUID id,

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
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
        @Digits(integer = 17, fraction = 2)
        BigDecimal price,

        @NotNull
        Boolean active,

        @NotNull
        Gender gender,

        @NotNull
        ProductType type,

        @Valid
        @NotNull
        List<@Valid ProductVariant> productVariants

) {
    public Product {
        if (price != null) {
            price = price.setScale(2, RoundingMode.HALF_UP);
        }
        if (productVariants == null) {
            productVariants = List.of();
        }
    }
}
