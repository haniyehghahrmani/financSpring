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

@Entity(name = "TransactionTypeEntity")
@Table(name = "transaction_types")
@Cacheable
public class TransactionType extends Base{

    @Id
    @SequenceGenerator(name = "transactionTypeSeq", sequenceName = "transaction_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionTypeSeq")
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name", length = 200, nullable = false)
    @NotBlank(message = "Transaction type name should not be blank")
    @Size(min = 3, max = 200, message = "Transaction type name must be between 3 and 200 characters")
    private String name;

    @Column(name = "t_is_active")
    private Boolean isActive = true;
}
