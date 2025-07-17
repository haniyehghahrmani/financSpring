package org.java.financespring.model.pgmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.java.financespring.model.h2model.Base;
import org.java.financespring.model.h2model.PayrollStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PayrollEntity")
@Table(name = "payrolls")
public class Payroll extends Base {

    @Id
    @SequenceGenerator(name = "payrollSeq", sequenceName = "payroll_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payrollSeq")
    @Column(name = "p_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "Employee must not be null")
    @JsonIgnore
    private Employee employee;

    @JsonProperty("employeeCode")
    private String getEmployeeCode() {
        return employee != null ? employee.getEmployeeCode() : null;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payroll_status_id", nullable = false)
    private PayrollStatus status;

    //تاریخ شروع و پایان دوره پرداخت حقوق
    @Column(name = "p_pay_period_start", nullable = false)
    @NotNull(message = "Start date must not be null")
    private LocalDate payPeriodStart;

    @Transient
    private String faPayPeriodStart;

    @Column(name = "p_pay_period_end", nullable = false)
    @NotNull(message = "End date must not be null")
    private LocalDate payPeriodEnd;

    @Transient
    private String faPayPeriodEnd;

    //حقوق پایه
    @Column(name = "p_base_salary", precision = 15, scale = 2, nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Base salary must be non-negative")
    private BigDecimal baseSalary;

    //مزایا
    @Column(name = "p_allowances", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Allowances must be non-negative")
    private BigDecimal allowances;

    //پرداخت اضافه‌کاری
    @Column(name = "p_overtime_pay", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Overtime pay must be non-negative")
    private BigDecimal overtimePay;

    //جریمه تأخیر
    @Column(name = "p_delay_penalty", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Delay penalty must be non-negative")
    private BigDecimal delayPenalty;

    //مبلغ بیمه‌ای که از حقوق کم شده
    @Column(name = "p_insurance_withheld", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Insurance withheld must be non-negative")
    private BigDecimal insuranceWithheld;

    //مبلغ مالیات کم‌شده
    @Column(name = "p_tax_withheld", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Tax withheld must be non-negative")
    private BigDecimal taxWithheld;

    //حقوق نهایی
    @Column(name = "p_net_salary", precision = 15, scale = 2, nullable = false)
    @NotNull(message = "Net salary must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Net salary must be non-negative")
    private BigDecimal netSalary;

    @Column(name = "p_created_date")
    private LocalDate createdDate;

    @Column(name = "p_last_updated")
    private LocalDate lastUpdated;

    public String getFaPayPeriodStart() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(payPeriodStart)));
    }

    public void setFaPayPeriodStart(String faPayPeriodStart) {
        this.payPeriodStart = LocalDate.from(PersianDate.parse(faPayPeriodStart).toGregorian().atStartOfDay());
    }

    public String getFaPayPeriodEnd() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(payPeriodEnd)));
    }

    public void setFaPayPeriodEnd(String faPayPeriodEnd) {
        this.payPeriodEnd = LocalDate.from(PersianDate.parse(faPayPeriodEnd).toGregorian().atStartOfDay());
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDate.now();
    }
}