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

@Entity(name = "TransactionStatusEntity")
@Table(name = "TransactionStatusTbl")
public class TransactionStatus {

    @Id
    @SequenceGenerator(name = "transactionStatusSeq", sequenceName = "transactionStatus_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionStatusSeq")
    @Column(name = "transactionStatus_id")
    private Long id;

    @Column(name = "transactionStatus_name", length = 200, nullable = false)
    @NotBlank(message = "Transaction Status name should not be blank")
    @Size(min = 3, max = 200, message = "Transaction Status name must be between 3 and 200 characters")
    private String name;
}
