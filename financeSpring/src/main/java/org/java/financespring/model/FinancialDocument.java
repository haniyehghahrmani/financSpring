package org.java.financespring.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "FinancialDocumentEntity")
@Table(name = "FinancialDocumentTbl")
public class FinancialDocument {

    @Id
    @SequenceGenerator(name = "financialDocumentSeq", sequenceName = "financial_document_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocumentSeq")
    @Column(name = "financial_document_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "document_type_id", nullable = false)
    @NotNull(message = "Document type should not be null")
    private FinancialDocumentType documentType;

    @Column(name = "document_number", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{1,20}$", message = "Invalid Document Number")
    @Size(min = 3, max = 200, message = "Document numbern must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String documentNumber;

    @Column(name = "document_date", nullable = false)
    @PastOrPresent(message = "Invalid Document Date")
    @NotNull(message = "Document date should not be null")
    private LocalDate documentDate;

    @Transient
    private String faDocumentDate;

    @Column(name = "total_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Total amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal totalAmount;

    @Column(name = "paid_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Paid amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal paidAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Status should not be null")
    private FinancialDocumentStatus status;

    @Column(name = "description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "created_by_user_id", nullable = false)
    @NotNull(message = "Account should not be null")
    private User createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "financialDocument", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @Transient
    public BigDecimal getRemainingAmount() {
        if (totalAmount == null || paidAmount == null) {
            return BigDecimal.ZERO;
        }
        return totalAmount.subtract(paidAmount);
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    public String getFaDocumentDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(documentDate)));
    }

    public void setFaDocumentDate(String faDocumentDate) {
        this.documentDate = LocalDate.from(PersianDate.parse(faDocumentDate).toGregorian().atStartOfDay());
    }
}