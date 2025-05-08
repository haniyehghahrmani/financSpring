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
@Table(name = "TransactionTypeTbl")
@Cacheable
public class TransactionType {

    @Id
    @SequenceGenerator(name = "transactionTypeSeq", sequenceName = "transactionType_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionTypeSeq")
    @Column(name = "transactionType_id")
    private Long id;

    @Column(name = "transactionType_name", length = 200, nullable = false)
    @NotBlank(message = "Transaction type name should not be blank")
    @Size(min = 3, max = 200, message = "Transaction type name must be between 3 and 200 characters")
    private String name;
}
