package org.java.financespring.model.pgmodel;

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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "EmployeeEntity")
@Table(name = "employees")
public class Employee extends Base {

    @Id
    @SequenceGenerator(name = "employeeSeq", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    @Column(name = "e_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "e_code", unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{1,20}$", message = "Invalid code")
    @Size(min = 1, max = 20, message = "code must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String employeeCode;

    // تاریخ استخدام
    @Column(name = "e_hire_date")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @Transient
    private String faHireDate;

    @Column(name = "e_job_title", length = 100)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{2,50}$", message = "Invalid Job Title")
    @Size(min = 2, max = 50, message = "job title must be between 2 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String jobTitle;

    @Column(name = "e_department", length = 100)
    @Size(max = 100, message = "Department name must be less than or equal to 100 characters")
    private String department;

    // حقوق پایه
    @Column(name = "et_base_salary", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Base salary must be non-negative")
    private BigDecimal baseSalary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Account account;

    @Column(name = "e_insurance_number", length = 20)
    @Size(max = 20, message = "Insurance number must be less than or equal to 20 characters")
    private String insuranceNumber;

    @Column(name = "e_is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @NotNull(message = "Employment type must be specified")
    private EmploymentType employmentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "e_salary_structure_id")
    private SalaryStructure salaryStructure;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
    private List<Attendance> attendance;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequest;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
    private List<Bonus> bonus;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST},  fetch = FetchType.LAZY)
    private List<Deduction> deduction;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Payroll> payroll;

    public String getFaHireDate() {
        if (faHireDate != null) {
            return PersianDate.fromGregorian(hireDate).toString();
        }
        return null;
    }

    public void setFaHireDate(String faHireDate) {
        if (faHireDate != null && !faHireDate.isEmpty()) {
            this.hireDate=PersianDate.parse(faHireDate).toGregorian();
        }
    }
}