package dev.skoleff.aureline.controller;

import dev.skoleff.aureline.model.ProductVariant;
import dev.skoleff.aureline.service.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/variants")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public List<ProductVariant> getAllVariants() {
        return productVariantService.getAllVariants();
    }

    @GetMapping("/actives")
    public List<ProductVariant> getAllVariantsActivesAndExists() {
        return productVariantService.getAllVariantsActivesAndExist();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductVariant> getVariantById(@PathVariable Long id) {
        return productVariantService.getVariantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductVariant createVariant(@RequestBody ProductVariant variant) {
        return productVariantService.saveVariant(variant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariant> updateVariant(
            @PathVariable Long id,
            @RequestBody ProductVariant variant
    ) {
        if (!productVariantService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        variant.setId(id);
        return ResponseEntity.ok(productVariantService.saveVariant(variant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        if (!productVariantService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productVariantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }
}
