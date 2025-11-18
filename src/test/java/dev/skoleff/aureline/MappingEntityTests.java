package dev.skoleff.aureline;


import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;
import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductEntity;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductVariantEntity;
import dev.skoleff.aureline.infrastructure.persistence.entity.mapper.ProductEntityMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingEntityTests {

    ProductEntityMapper productEntityMapper = Mappers.getMapper(ProductEntityMapper.class);

    @Test
    void test(){
        var productId  = UUID.randomUUID();
        var productVariantId = UUID.randomUUID();

        ProductEntity productEntity = new ProductEntity(
                productId,
                "name",
                "model",
                "description",
                BigDecimal.valueOf(120),
                true,
                Gender.HOMBRE,
                ProductType.ACCESORIO,
                "nike",
                List.of());

        ProductVariantEntity productVariantEntity = new ProductVariantEntity(productVariantId, "sku", "M", "red", 10, BigDecimal.valueOf(100),productEntity);

        productEntity.setProductVariants(List.of(productVariantEntity));

        Product product = productEntityMapper.toDomain(productEntity);

        assertThat(product).isNotNull();
        assertThat(product.id()).isEqualTo(productEntity.getId());
        assertThat(product.name()).isEqualTo(productEntity.getName());
        assertThat(product.productVariants().get(0)).isNotNull();
        assertThat(product.productVariants().get(0).id()).isEqualTo(productVariantEntity.getId());
    }
}
