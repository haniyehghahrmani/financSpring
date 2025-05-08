package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PayrollStatusEntity")
@Table(name = "PayrollStatusTbl")
@Cacheable
public class PayrollStatus {

    @Id
    @SequenceGenerator(name = "payrollStatusSeq", sequenceName = "payrollStatus_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payrollStatusSeq")
    @Column(name = "payrollStatus_id")
    private Long id;

    @Column(name = "payrollStatus_name", length = 200, nullable = false)
    @NotBlank(message = "Status name should not be blank")
    @Size(min = 3, max = 200, message = "Status name must be between 3 and 200 characters")
    private String name;
}