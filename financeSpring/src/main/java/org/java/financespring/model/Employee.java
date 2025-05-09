package org.java.financespring.model;

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
public class Employee {

    @Id
    @SequenceGenerator(name = "employeeSeq", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    @Column(name = "e_id")
    private Long id;

    @Column(name = "e_first_name", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,100}$", message = "Invalid first name")
    @Size(min = 3, max = 100, message = "first name must be between 3 and 100 characters")
    @NotBlank(message = "Should Not Be Null")
    private String firstName;

    @Column(name = "e_last_name", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,100}$", message = "Invalid last name")
    @Size(min = 3, max = 100, message = "last name must be between 3 and 100 characters")
    @NotBlank(message = "Should Not Be Null")
    private String lastName;

    @Column(name = "e_code", unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{1,20}$", message = "Invalid code")
    @Size(min = 1, max = 20, message = "code must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String employeeCode;

    @Column(name = "e_national_id", length = 10, unique = true)
    @Pattern(regexp = "^[0-9]{1,10}$", message = "Invalid National ID")
    @Size(min = 1, max = 10, message = " National ID must be between 1 and 10 characters")
    @NotBlank(message = "Should Not Be Null")
    private String nationalId;

    @Column(name = "e_phoneNumber", length = 11, unique = true)
    @Pattern(regexp = "^[0-9]{1,11}$", message = "Invalid phoneNumber")
    @Size(min = 1, max = 11, message = " phoneNumber must be between 1 and 11 characters")
    @NotBlank(message = "Should Not Be Null")
    private String phoneNumber;

    @Column(name = "e_email", length = 100, unique = true)
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String email;

    // تاریخ استخدام
    @Column(name = "e_hire_date")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @Column(name = "e_job_title", length = 100)
    @Size(max = 100, message = "Job title must be less than or equal to 100 characters")
    private String jobTitle;

    @Column(name = "e_department", length = 100)
    @Size(max = 100, message = "Department name must be less than or equal to 100 characters")
    private String department;

    // حقوق پایه
    @Column(name = "et_base_salary", precision = 15, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true, message = "Base salary must be non-negative")
    private BigDecimal baseSalary;

    @Column(name = "e_bank_name", length = 100)
    @Size(max = 100, message = "Bank name must be less than or equal to 100 characters")
    private String bankName;

    @Column(name = "e_bank_account_number", length = 50)
    @Size(max = 50, message = "Bank account number must be less than or equal to 50 characters")
    private String bankAccountNumber;

    @Column(name = "e_sheba_number", length = 26)
    @Pattern(regexp = "^IR[0-9]{24}$", message = "Invalid Sheba number")
    private String shebaNumber;

    @Column(name = "e_insurance_number", length = 20)
    @Size(max = 20, message = "Insurance number must be less than or equal to 20 characters")
    private String insuranceNumber;

    @Column(name = "e_is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "e_birth_date")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Employment type must be specified")
    private EmploymentType employmentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "e_salary_structure_id")
    private SalaryStructure salaryStructure;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Attendance> attendance;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequest;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bonus> bonus;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Deduction> deduction;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payroll> payroll;
}