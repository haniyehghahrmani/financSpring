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

@Entity(name = "EmploymentTypeEntity")
@Table(name = "EmploymentTypeTbl")
public class EmploymentType {

    @Id
    @SequenceGenerator(name = "employmentTypeSeq", sequenceName = "employmentType_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employmentTypeSeq")
    @Column(name = "employmentType_id")
    private Long id;

    @Column(name = "employmentType_name", length = 200, nullable = false)
    @NotBlank(message = "Financial document type name should not be blank")
    @Size(min = 3, max = 200, message = "Financial document type name must be between 3 and 200 characters")
    private String name;
}