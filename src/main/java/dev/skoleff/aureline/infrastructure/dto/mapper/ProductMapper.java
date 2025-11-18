package dev.skoleff.aureline.infrastructure.dto.mapper;

import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.domain.model.ProductVariant;
import dev.skoleff.aureline.infrastructure.dto.*;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Product toDomain(ProductCreateRequest request);

    @Mapping(target = "id", ignore = true)
    ProductVariant toDomain(ProductVariantCreateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "active", ignore = true)
    Product toDomain(UUID id, ProductUpdateRequest request);

    @Mapping(target = "id", source = "request.id")
    ProductVariant toDomain(ProductVariantUpdateRequest request);

    ProductResponse toResponse(Product product);

    ProductVariantResponse toResponse(ProductVariant variant);

    java.util.List<ProductResponse> toResponseList(java.util.List<Product> products);
}
