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

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "BonusEntity")
@Table(name = "bonuses")
public class Bonus extends Base {

    @Id
    @SequenceGenerator(name = "bonusSeq", sequenceName = "bonus_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bonusSeq")
    @Column(name = "b_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private Employee employee;

    @JsonProperty("employeeCode")
    private String getEmployeeCode() {
        return employee != null ? employee.getEmployeeCode() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "payroll_id")
//    @JoinColumn(name = "payroll_id", nullable = false)
    @JsonIgnore
    private Payroll payroll;

    @JsonProperty("payrollId")
    private Long getPayrollId() {
        return payroll != null ? payroll.getId() : null;
    }

    @Column(name = "b_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "b_reason", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Reason")
    @Size(min = 3, max = 200, message = "Reason must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String reason;

    @Column(name = "b_granted_date", nullable = false)
    @PastOrPresent(message = "Invalid Granted Date")
    @NotNull(message = "Granted date should not be null")
    private LocalDate grantedDate;

    @Transient
    private String faGrantedDate;

    // آیا این پاداش شامل مالیات هست یا نه
    @Column(name = "b_taxable", nullable = false)
    @NotNull(message = "taxable must not be null")
    private boolean taxable;

    public String getFaGrantedDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(grantedDate)));
    }

    public void setFaGrantedDate(String faGrantedDate) {
        this.grantedDate = LocalDate.from(PersianDate.parse(faGrantedDate).toGregorian().atStartOfDay());
    }
}