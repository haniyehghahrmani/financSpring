package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "ProductEntity")
@Table(name = "products")
@Cacheable
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name", length = 100, nullable = false)
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name must be at most 100 characters")
    private String name;

    @Column(name = "p_description", columnDefinition = "NVARCHAR2(500)")
    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @Column(name = "p_price", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than zero")
    private BigDecimal price;

    @Column(name = "p_stock_quantity", nullable = false)
    @Min(value = 0, message = "Stock quantity cannot be less than zero")
    private Integer stockQuantity;

    @Column(name = "p_is_active")
    private Boolean isActive = true;

    @Column(name = "p_created_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "p_last_updated")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
