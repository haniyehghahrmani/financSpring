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
@Table(name = "payroll_statuses")
@Cacheable
public class PayrollStatus extends Base{

    @Id
    @SequenceGenerator(name = "payrollStatusSeq", sequenceName = "payroll_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payrollStatusSeq")
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name", length = 200, nullable = false)
    @NotBlank(message = "Status name should not be blank")
    @Size(min = 3, max = 200, message = "Status name must be between 3 and 200 characters")
    private String name;
}