package dev.skoleff.aureline.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_size_color", columnNames = {"product_id", "size", "color"}),
                @UniqueConstraint(name = "uk_variant_sku", columnNames = {"sku"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // producto [2] + color [3] + talle [2]
    @Column(length = 50, unique = true)
    private String sku;

    @Column(length = 20)
    private String size;

    @Column(length = 30)
    private String color;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;


}
