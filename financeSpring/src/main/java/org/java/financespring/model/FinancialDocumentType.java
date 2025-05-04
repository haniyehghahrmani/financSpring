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

@Entity(name = "FinancialDocumentTypeEntity")
@Table(name = "FinancialDocumentTypeTbl")
public class FinancialDocumentType {

    @Id
    @SequenceGenerator(name = "financialDocumentTypeSeq", sequenceName = "financialDocumentType_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocumentTypeSeq")
    @Column(name = "FinancialDocumentType_id")
    private Long id;

    @Column(name = "FinancialDocumentType_name", length = 200, nullable = false)
    @NotBlank(message = "Financial document type name should not be blank")
    @Size(min = 3, max = 200, message = "Financial document type name must be between 3 and 200 characters")
    private String name;
}