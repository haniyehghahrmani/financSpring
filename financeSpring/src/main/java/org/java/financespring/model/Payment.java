package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "PaymentEntity")
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "payment_type")
@Cacheable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Payment {

    @Id
    @SequenceGenerator(name = "paymentSeq", sequenceName = "payment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSeq")
    @Column(name = "p-id")
    private Long paymentID;

    @Column(name = "p-transaction_id", nullable = false)
    @NotNull(message = "Transaction ID is required")
    private Integer transactionID;

    @Column(name = "p-amount", nullable = false, precision = 18, scale = 2)
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "p-date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Payment date is required")
    private Date paymentDate;

    @Column(name = "p-status", length = 50)
    @Size(max = 50)
    private String status;

    @Column(name = "p-description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "p-created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "p-last_updated")
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