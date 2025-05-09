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
public class InvoiceItem extends Base{

    @Id
    @SequenceGenerator(name = "invoiceItemSeq", sequenceName = "invoice_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceItemSeq")
    @Column(name = "i_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_invoice", nullable = false)
    private Invoice invoice;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "i_description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "i_unit_price", nullable = false, precision = 18, scale = 2)
    @NotNull
    private BigDecimal unitPrice;

    @Column(name = "i_quantity", nullable = false)
    @Min(1)
    private Integer quantity;

    @Column(name = "i_total_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "i_discount", precision = 18, scale = 2)
    private BigDecimal discount;

    @Column(name = "i_final_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal finalPrice;

    @Column(name = "i_created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "i_last_updated")
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

    @Column(name = "i_is_active")
    private Boolean isActive = true;
}
