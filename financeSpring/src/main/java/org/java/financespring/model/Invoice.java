package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "InvoiceEntity")
@Table(name = "invoices")
@Cacheable
public class Invoice {

    @Id
    @SequenceGenerator(name = "invoiceSeq", sequenceName = "invoice_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceSeq")
    @Column(name = "id")
    private Long id;

    @Column(name = "i_number", nullable = false, length = 50)
    @NotBlank(message = "Invoice number is required")
    @Size(max = 50)
    private String invoiceNumber;

    @Column(name = "i_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date invoiceDate;

    @Column(name = "i_due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dueDate;

    @Column(name = "i_total_amount", nullable = false, precision = 18, scale = 2)
    @NotNull
    private BigDecimal totalAmount;

    @Column(name = "i_paid_amount", precision = 18, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "i_remaining_amount", precision = 18, scale = 2)
    private BigDecimal remainingAmount;

    @Column(name = "i_status", length = 50)
    @Size(max = 50)
    private String invoiceStatus;

    @Column(name = "i_customer_name", length = 100)
    @Size(max = 100)
    private String customerName;

    @Column(name = "i_customer_address", length = 255)
    @Size(max = 255)
    private String customerAddress;

    @Column(name = "i_customer_phone", length = 20)
    @Size(max = 20)
    private String customerPhone;

    @Column(name = "i_description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "i_account_id", nullable = false)
    @NotNull
    private Integer accountID;

    @Column(name = "i_user_id", nullable = false)
    @NotNull
    private Integer userID;

    @Column(name = "i_created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "i_last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

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
