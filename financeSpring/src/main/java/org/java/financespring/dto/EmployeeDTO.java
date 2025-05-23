package org.java.financespring.dto;

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

public class EmployeeDTO {

    private Long id;
    private String employeeCode;
    private String jobTitle;
    private String department;
    private LocalDate hireDate;
    private BigDecimal baseSalary;
    private boolean isActive;
    private String employmentTypeName;
}
