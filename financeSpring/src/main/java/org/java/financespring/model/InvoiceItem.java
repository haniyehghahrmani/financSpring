package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "InvoiceItemEntity")
@Table(name = "invoice_items")
@Cacheable
public class InvoiceItem {

    @Id
    @SequenceGenerator(name = "invoiceItemSeq", sequenceName = "invoice_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceItemSeq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_id", nullable = false)
    private Invoice invoice;

    @Column(name = "product_name", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String productName;

    @Column(name = "description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "unit_price", nullable = false, precision = 18, scale = 2)
    @NotNull
    private BigDecimal unitPrice;

    @Column(name = "quantity", nullable = false)
    @Min(1)
    private Integer quantity;

    @Column(name = "total_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "discount", precision = 18, scale = 2)
    private BigDecimal discount;

    @Column(name = "final_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal finalPrice;

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Date();
    }

    @Column(name = "is_active")
    private Boolean isActive = true;
}
