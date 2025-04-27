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
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "TransactionEntity")
@Table(name = "TransactionTbl")
public class Transaction {

    @Id
    @SequenceGenerator(name = "transactionSeq", sequenceName = "transaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeq")
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "transaction_date", nullable = false)
    @PastOrPresent(message = "Invalid Transaction Date")
    @NotNull(message = "Transaction date should not be null")
    private LocalDateTime transactionDate;

    @Transient
    private String faTransactionDate;

    @Column(name = "transaction_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Column(name = "transaction_type", length = 200, nullable = false)
    @NotBlank(message = "Transaction type should not be blank")
    @Size(min = 3, max = 200, message = "Transaction type must be between 3 and 200 characters")
    private String transactionType;

    @Column(name = "description", length = 500)
    @Size(max = 500, message = "Description can be up to 500 characters")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "Account should not be null")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", length = 100, nullable = false)
//    @NotNull(message = "Status should not be null")
//    private TransactionStatus status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    @NotNull(message = "User should not be null")
//    private User user;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "payment_method_id")
//    private PaymentMethod paymentMethod;

    // Automatically set createdDate and lastUpdated
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        lastUpdated = createdDate;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    // Method to convert Persian date-time to Gregorian LocalDateTime
    public static LocalDateTime convertPersianToGregorianDateTime(String faTransactionDate) {
        try {
            // Split Persian Date and Time
            String[] parts = faTransactionDate.split(" ");
            String persianDatePart = parts[0];
            String timePart = parts[1];

            // Split Persian Date into year, month, day
            String[] dateParts = persianDatePart.split("/");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            // Split Time into hour, minute
            String[] timeParts = timePart.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            // Convert Persian Date to Gregorian LocalDate
            PersianDate persianDate = PersianDate.of(year, month, day);
            LocalDateTime gregorianDateTime = persianDate.toGregorian().atTime(hour, minute); // تبدیل به LocalDateTime

            return gregorianDateTime;

        } catch (Exception e) {
            throw new RuntimeException("Invalid Persian DateTime format. Expected format: yyyy/MM/dd HH:mm", e);
        }
    }

    // Setter to automatically convert Persian Date to Gregorian LocalDateTime
    public void setFaTransactionDate(String faTransactionDate) {
        this.faTransactionDate = faTransactionDate;
        this.transactionDate = convertPersianToGregorianDateTime(faTransactionDate); // For transactionDate
        this.createdDate = this.transactionDate; // For createdDate
    }
}
