package dev.skoleff.aureline.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public record ProductVariant(

        UUID id,

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
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @Digits(integer = 17, fraction = 2)
        BigDecimal price

) {
    public ProductVariant {
        if(price != null){
            price = price.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
