package org.java.financespring.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
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

@MappedSuperclass
public abstract class PaymentMethod {

    @Column(name = "pm_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "pm_date", nullable = false)
    @NotNull(message = "Payment method date should not be null")
    @PastOrPresent(message = "Payment method date must be in the past or present")
    private LocalDate date;

    @Column(name = "pm_time", nullable = false)
    @NotNull(message = "Time should not be null")
    @PastOrPresent(message = "Time must be in the past or present")
    private LocalTime time;

    @Transient
    private String faDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "pm_attachment_id")
    private Attachment paymentMethodDoc;

    @Column(name = "pm_description", length = 200, columnDefinition = "NVARCHAR2(200)")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid description")
    private String description;

    @Column(name = "pm_created_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "pm_last_updated")
    private LocalDateTime updatedAt;

    @Column(name = "pm_is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getFaDate() {
        if (this.date != null) {
            return PersianDate.fromGregorian(this.date).toString();
        }
        return null;
    }

    public void setFaDate(String faDate) {
        if (faDate != null && !faDate.isBlank()) {
            this.date = PersianDate.parse(faDate).toGregorian();
        }
    }
}
