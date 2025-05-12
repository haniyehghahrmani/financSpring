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

@Entity(name = "PaymentEntity")
@Table(name = "payments")
public class Payment extends Base{

    @Id
    @SequenceGenerator(name = "paymentSeq", sequenceName = "payment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSeq")
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_amount", nullable = false, precision = 18, scale = 2)
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "p_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Payment date is required")
    private Date paymentDate;

//    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private PaymentStatus paymentStatus;

    @Column(name = "p_description", length = 500)
    @Size(max = 500)
    private String description;

    @Column(name = "p_created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "p_last_updated")
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

    @Column(name = "p_is_active")
    private Boolean isActive = true;
}