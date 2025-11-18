package dev.skoleff.aureline.infrastructure.persistence.entity.mapper;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.model.ProductVariant;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductEntity;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductVariantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntityMapper INSTANCE = Mappers.getMapper( ProductEntityMapper.class );

    Product toDomain(ProductEntity productEntity);

    ProductEntity toEntity(Product product);

    ProductVariant toDomain(ProductVariantEntity productVariantEntity);

    ProductVariantEntity toEntity(ProductVariant productVariant);

}

