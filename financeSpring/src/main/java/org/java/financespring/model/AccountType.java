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

@Entity(name = "AccountTypeEntity")
@Table(name = "account_types")
@Cacheable
public class AccountType {

    @Id
    @SequenceGenerator(name = "accountTypeSeq", sequenceName = "account_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountTypeSeq")
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_name", length = 200, nullable = false)
    @NotBlank(message = "Account type name should not be blank")
    @Size(min = 3, max = 200, message = "Account type name must be between 3 and 200 characters")
    private String name;
}