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

@Entity(name = "FinancialDocumentStatusEntity")
@Table(name = "FinancialDocumentStatusTbl")
public class FinancialDocumentStatus {

    @Id
    @SequenceGenerator(name = "financialDocumentStatusSeq", sequenceName = "financialDocumentStatus_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocumentStatusSeq")
    @Column(name = "financialDocumentStatus_id")
    private Long id;

    @Column(name = "financialDocumentStatus_name", length = 200, nullable = false)
    @NotBlank(message = "Financial documentStatus name should not be blank")
    @Size(min = 3, max = 200, message = "Financial documentStatus name must be between 3 and 200 characters")
    private String name;
}
