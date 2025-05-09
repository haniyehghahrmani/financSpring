package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "SalaryStructureEntity")
@Table(name = "salary_structures")
public class SalaryStructure extends Base{

    @Id
    @SequenceGenerator(name = "salaryStructureSeq", sequenceName = "salary_structure_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salaryStructureSeq")
    @Column(name = "s_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    @NotNull(message = "Employee must not be null")
    private Employee employee; // ارتباط با کارمند

    //حقوق پایه
    @Column(name = "s_base_salary", precision = 15, scale = 2, nullable = false)
    @NotNull(message = "Base salary must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Base salary must be non-negative")
    private BigDecimal baseSalary;

    //کمک‌هزینه مسکن
    @Column(name = "s_housing_allowance", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Housing allowance must be non-negative")
    private BigDecimal housingAllowance = BigDecimal.ZERO;

    //کمک‌هزینه فرزند
    @Column(name = "s_children_allowance", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Children allowance must be non-negative")
    private BigDecimal childrenAllowance = BigDecimal.ZERO;

    //کمک‌هزینه ایاب‌وذهاب
    @Column(name = "s_transportation_allowance", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Transportation allowance must be non-negative")
    private BigDecimal transportationAllowance = BigDecimal.ZERO;

    //کمک‌هزینه غذا
    @Column(name = "s_meal_allowance", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Meal allowance must be non-negative")
    private BigDecimal mealAllowance = BigDecimal.ZERO;

    //کسورات بیمه
    @Column(name = "s_insurance_deduction", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Insurance deduction must be non-negative")
    private BigDecimal insuranceDeduction = BigDecimal.ZERO;

    //کسورات مالیات
    @Column(name = "s_tax_deduction", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Tax deduction must be non-negative")
    private BigDecimal taxDeduction = BigDecimal.ZERO;

    //کسورات دیگر
    @Column(name = "s_other_deductions", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Other deductions must be non-negative")
    private BigDecimal otherDeductions = BigDecimal.ZERO;

    //حقوق خالص =
    //    حقوق پایه
    //  + مسکن
    //  + فرزند
    //  + حمل‌ونقل
    //  + غذا
    //  - بیمه
    //  - مالیات
    //  - سایر کسورات
    public BigDecimal getTotalSalary() {
        return baseSalary
                .add(housingAllowance)
                .add(childrenAllowance)
                .add(transportationAllowance)
                .add(mealAllowance)
                .subtract(insuranceDeduction)
                .subtract(taxDeduction)
                .subtract(otherDeductions);
    }
}