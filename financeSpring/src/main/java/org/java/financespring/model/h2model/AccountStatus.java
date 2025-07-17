package org.java.financespring.model.h2model;

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

@Entity(name = "AccountStatusEntity")
@Table(name = "account_statuses")
@Cacheable
public class AccountStatus extends Base {

    @Id
    @SequenceGenerator(name = "accountStatusSeq", sequenceName = "account_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountStatusSeq")
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_name", length = 200, nullable = false)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;
}