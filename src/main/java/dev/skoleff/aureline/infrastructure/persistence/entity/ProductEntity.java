package dev.skoleff.aureline.infrastructure.persistence.entity;

import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 100)
    private String model;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;


    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ProductType type;

    @Column(length = 50)
    private String brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> productVariants;

    @Default
    public ProductEntity(){
    }

    public ProductEntity(UUID id, String name, String model, String description, BigDecimal price, Boolean active, Gender gender, ProductType type, String brand, List<ProductVariantEntity> productVariants) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.description = description;
        this.price = price;
        this.active = active;
        this.gender = gender;
        this.type = type;
        this.brand = brand;
        this.productVariants = productVariants;
    }

    public ProductEntity(String name, String model, String description, BigDecimal price, Boolean active, Gender gender, ProductType type, String brand, List<ProductVariantEntity> productVariants) {
        this.name = name;
        this.model = model;
        this.description = description;
        this.price = price;
        this.active = active;
        this.gender = gender;
        this.type = type;
        this.brand = brand;
        this.productVariants = productVariants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductVariantEntity> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantEntity> productVariants) {
        this.productVariants = productVariants;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
