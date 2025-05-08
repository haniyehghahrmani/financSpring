package org.java.financespring.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PaymentMethodEntity")
@Table(name = "PaymentMethodTbl")
public class PaymentMethod {

    @Id
    @SequenceGenerator(name = "paymentMethodSeq", sequenceName = "payment_method_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentMethodSeq")
    @Column(name = "payment_method_id")
    private Long id;

    @Column(name = "payment_method_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "payment_method_date_time", nullable = false)
    @PastOrPresent(message = "Invalid Payment Method Date Time")
    @NotNull(message = "payment method date time should not be null")
    private LocalDate date;

    @Column(name = "payment_method_time", nullable = false)
    @PastOrPresent(message = "Invalid  Time")
    @NotNull(message = "Time should not be null")
    private LocalTime time;

    @Transient
    private String faDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Attachment paymentMethodDoc;

    @Column(name = "payment_method_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String description;

    @Column(name = "payment_method_created_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "payment_method_last_updated")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(date)));
    }

    public void setFaDate(String faDate) {
        this.date = LocalDate.from(PersianDate.parse(faDate).toGregorian().atStartOfDay());
    }
}