package dev.skoleff.aureline.controller;


import dev.skoleff.aureline.model.Product;
import dev.skoleff.aureline.model.ProductImage;
import dev.skoleff.aureline.repository.ProductRepository;
import dev.skoleff.aureline.service.ProductImageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {
    private final ProductImageService imageService;
    private final ProductRepository productRepository;

    @Value("${app.upload.dir:aureline-images}")
    private String uploadDir;

    public ProductImageController(ProductImageService imageService,
                                  ProductRepository productRepository) {
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductImage> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId,
            @RequestParam(value = "altText", required = false) String altText
    ) throws IOException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        String extension = file.getOriginalFilename() != null
                ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
                : "";
        String filename = UUID.randomUUID() + extension;
        Path filePath = Paths.get(uploadDir, filename);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        ProductImage savedImage = imageService.saveImage(
                uploadDir + "/" + filename,
                product,
                altText
        );

        return ResponseEntity.ok(savedImage);
    }

}
