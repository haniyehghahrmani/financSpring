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
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "TransactionEntity")
@Table(name = "transactions")
public class Transaction {

    @Id
    @SequenceGenerator(name = "transactionSeq", sequenceName = "transaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeq")
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_date", nullable = false)
    @PastOrPresent(message = "Invalid Transaction Date")
    @NotNull(message = "Transaction date should not be null")
    private LocalDate transactionDate;

    @Transient
    private String faTransactionDate;

    @Column(name = "t_time", nullable = false)
    @PastOrPresent(message = "Invalid Transaction Time")
    @NotNull(message = "Transaction time should not be null")
    private LocalTime transactionTime;

    @Column(name = "t_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "transactionType_id", nullable = false)
    @NotNull(message = "Account should not be null")
    private TransactionType type;

    @Column(name = "t_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    @NotNull(message = "Account should not be null")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "t_created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "t_last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    public String getFaTransactionDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(transactionDate)));
    }

    public void setFaCreateDate(String faTransactionDate) {
        this.transactionDate = LocalDate.from(PersianDate.parse(faTransactionDate).toGregorian().atStartOfDay());
    }
}