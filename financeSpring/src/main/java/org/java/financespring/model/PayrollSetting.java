package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Component

@Entity(name = "PayrollSettingEntity")
@Table(name = "PayrollSettingTbl")
public class PayrollSetting {

    @Id
    @SequenceGenerator(name = "payrollSettingSeq", sequenceName = "payrollSetting_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payrollSettingSeq")
    @Column(name = "payrollSetting_id")
    private Long id;

    // درصد مالیات کلی (مثلاً 10٪)
    @Column(name = "tax_rate", precision = 5, scale = 2, nullable = false)
    @NotNull(message = "Tax rate must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Tax rate must be non-negative")
    @DecimalMax(value = "100.0", message = "Tax rate must be less than or equal to 100")
    private BigDecimal taxRate;

    // نرخ اضافه‌کاری (مثلاً 1.5 برابر)
    @Column(name = "overtime_rate", precision = 5, scale = 2, nullable = false)
    @NotNull(message = "Overtime rate must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Overtime rate must be greater than zero")
    private BigDecimal overtimeRate;

    // درصد بیمه کسرشونده
    @Column(name = "insurance_rate", precision = 5, scale = 2, nullable = false)
    @NotNull(message = "Insurance rate must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Insurance rate must be non-negative")
    @DecimalMax(value = "100.0", message = "Insurance rate must be less than or equal to 100")
    private BigDecimal insuranceRate;

    // ساعات کاری ماهانه استاندارد (مثلاً 160 ساعت)
    @Column(name = "standard_monthly_hours", nullable = false)
    @Min(value = 1, message = "Standard monthly hours must be at least 1")
    private int standardMonthlyHours;

    // آیا پاداش‌ها در محاسبه مالیات لحاظ می‌شوند؟
    @Column(name = "include_bonuses_in_tax", nullable = false)
    private boolean includeBonusesInTax = true;

    // محاسبه خودکار کسورات
    @Column(name = "auto_calculate_deductions", nullable = false)
    private boolean autoCalculateDeductions = true;

    // ارز پیش‌فرض، مثلاً "IRR" یا "USD"
    @Column(name = "currency", length = 10, nullable = false)
    @NotBlank(message = "Currency must not be blank")
    @Size(min = 3, max = 10, message = "Currency code must be between 3 and 10 characters")
    private String currency;

    // تنظیم فعال فعلی
    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}