package dev.skoleff.aureline.service;

import dev.skoleff.aureline.model.Product;
import dev.skoleff.aureline.model.ProductImage;
import dev.skoleff.aureline.repository.ProductImageRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository){
        this.productImageRepository = productImageRepository;
    }

    public ProductImage saveImage(String url, Product product, String altText) {
        ProductImage image = new ProductImage();
        image.setUrl(url);
        image.setProduct(product);
        image.setAltText(altText);

        return productImageRepository.save(image);
    }

}
