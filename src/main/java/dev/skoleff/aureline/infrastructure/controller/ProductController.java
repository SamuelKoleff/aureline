package dev.skoleff.aureline.infrastructure.controller;

import dev.skoleff.aureline.application.*;
import dev.skoleff.aureline.domain.model.Product;
import dev.skoleff.aureline.infrastructure.dto.*;
import dev.skoleff.aureline.infrastructure.dto.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final GetActiveProductsUseCase getActiveProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final SaveProductUseCase saveProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final SaveProductImageUseCase saveProductImageUseCase;
    private final DeleteProductImageUseCase deleteProductImageUseCase;

    private final ProductMapper productMapper;

    public ProductController(GetActiveProductsUseCase getActiveProductsUseCase, GetProductByIdUseCase getProductByIdUseCase, SaveProductUseCase saveProductUseCase, DeleteProductUseCase deleteProductUseCase, SaveProductImageUseCase saveProductImageUseCase, DeleteProductImageUseCase deleteProductImageUseCase, ProductMapper productMapper) {
        this.getActiveProductsUseCase = getActiveProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.saveProductUseCase = saveProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.saveProductImageUseCase = saveProductImageUseCase;
        this.deleteProductImageUseCase = deleteProductImageUseCase;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getActiveProducts() {
        List<Product> products = getActiveProductsUseCase.execute();
        return ResponseEntity.ok(productMapper.toResponseList(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        return ResponseEntity.of(
                Optional.ofNullable(getProductByIdUseCase.execute(id))
                        .map(productMapper::toResponse)
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        Product domain = productMapper.toDomain(request);
        Product saved = saveProductUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        if (getProductByIdUseCase.execute(id) == null) {
            return ResponseEntity.notFound().build();
        }

        Product domain = productMapper.toDomain(id, request);
        Product updated = saveProductUseCase.execute(domain);

        return ResponseEntity.ok(productMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        Product existing = getProductByIdUseCase.execute(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        boolean deleted = deleteProductUseCase.execute(existing);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadImage(
            @PathVariable UUID id,
            @RequestParam MultipartFile file
    ) throws IOException {

        if (!isValidImageUpload(id, file)) {
            return ResponseEntity.badRequest().build();
        }

        String name = saveProductImageUseCase.execute(id.toString(), file.getBytes());

        if(name == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(name);
    }

    @DeleteMapping("/{id}/images/{fileName}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable UUID id,
            @PathVariable String fileName
    ) {
        if (getProductByIdUseCase.execute(id) == null) {
            return ResponseEntity.notFound().build();
        }

        boolean deleted = deleteProductImageUseCase.execute(id.toString(), fileName);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private boolean isValidImageUpload(UUID productId, MultipartFile file) {
        if (getProductByIdUseCase.execute(productId) == null) {
            return false;
        }

        String contentType = file.getContentType();
        return MediaType.IMAGE_JPEG_VALUE.equals(contentType);
    }
}
