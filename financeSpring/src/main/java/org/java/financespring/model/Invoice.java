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

    @Column(name = "due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dueDate;

    @Column(name = "total_amount", nullable = false, precision = 18, scale = 2)
    @NotNull
    private BigDecimal totalAmount;

    @Column(name = "paid_amount", precision = 18, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "remaining_amount", precision = 18, scale = 2)
    private BigDecimal remainingAmount;

    @Column(name = "invoice_status", length = 50)
    @Size(max = 50)
    private String invoiceStatus;

    @Column(name = "customer_name", length = 100)
    @Size(max = 100)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    @Size(max = 255)
    private String customerAddress;

    @Column(name = "customer_phone", length = 20)
    @Size(max = 20)
    private String customerPhone;

    @Column(name = "description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "account_id", nullable = false)
    @NotNull
    private Integer accountID;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userID;

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.lastUpdated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Date();
    }
}
