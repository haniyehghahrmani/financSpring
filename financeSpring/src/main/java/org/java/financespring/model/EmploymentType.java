package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "employment_types")
@Cacheable
public class EmploymentType extends Base{

    @Id
    @SequenceGenerator(name = "employmentTypeSeq", sequenceName = "employment_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employmentTypeSeq")
    @Column(name = "e_id")
    private Long id;

    @Column(name = "e_name", length = 200, nullable = false,unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{3,30}$", message = "Invalid name")
    @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters")
    @NotBlank(message = "employment types name should not be blank")
    private String name;
}