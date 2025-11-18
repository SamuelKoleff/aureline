package dev.skoleff.aureline.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_variants",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_size_color", columnNames = {"product_id", "size", "color"}),
                @UniqueConstraint(name = "uk_variant_sku", columnNames = {"sku"})
        })
public class ProductVariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // producto [2] + color [3] + talle [2]
    @Column(length = 50, unique = true)
    private String sku;

    @Column(length = 20)
    private String size;

    @Column(length = 30)
    private String color;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private ProductEntity product;


    @Default
    public ProductVariantEntity(){
    }

    public ProductVariantEntity(UUID id, String sku, String size, String color, Integer stock, BigDecimal price, ProductEntity product) {
        this.id = id;
        this.sku = sku;
        this.size = size;
        this.color = color;
        this.stock = stock;
        this.price = price;
        this.product = product;
    }

    public ProductVariantEntity(ProductEntity product, BigDecimal price, Integer stock, String color, String size, String sku) {
        this.product = product;
        this.price = price;
        this.stock = stock;
        this.color = color;
        this.size = size;
        this.sku = sku;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
