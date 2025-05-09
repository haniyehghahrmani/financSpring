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

@Entity(name = "AccountStatusEntity")
@Table(name = "account_statuses")
@Cacheable
public class AccountStatus extends Base{

    @Id
    @SequenceGenerator(name = "accountStatusSeq", sequenceName = "account_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountStatusSeq")
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_name", length = 200, nullable = false)
    @NotBlank(message = "Account status name should not be blank")
    @Size(min = 3, max = 200, message = "Account status name must be between 3 and 200 characters")
    private String name;
}