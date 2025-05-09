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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "DeductionEntity")
@Table(name = "deductions")
public class Deduction extends Base{

    @Id
    @SequenceGenerator(name = "deductionSeq", sequenceName = "deduction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deductionSeq")
    @Column(name = "d_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "Employee should not be null")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "payroll_id", nullable = false)
    @NotNull(message = "Payroll should not be null")
    private Payroll payroll;

    @Column(name = "d_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "d_reason", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Reason")
    @Size(min = 3, max = 200, message = "Reason must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String reason;

    @Column(name = "d_date", nullable = false)
    @PastOrPresent(message = "Invalid Date")
    @NotNull(message = "Date should not be null")
    private LocalDate deductionDate;

    @Transient
    private String faDeductionDate;

    // اگر تکرارشونده باشه (مثلاً ماهانه)
    @Column(name = "d_is_recurring", nullable = false)
    @NotNull(message = "taxable must not be null")
    private boolean isRecurring;

    public String getFaDeductionDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(deductionDate)));
    }

    public void setFaDeductionDate(String faDeductionDate) {
        this.deductionDate = LocalDate.from(PersianDate.parse(faDeductionDate).toGregorian().atStartOfDay());
    }
}