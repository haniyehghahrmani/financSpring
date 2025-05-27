package org.java.financespring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "AccountEntity")
@Table(name = "accounts")
public class Account extends Base{

    @Id
    @SequenceGenerator(name = "accountSeq", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_name", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Account Name")
    @Size(min = 3, max = 200, message = "Account name must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String accountName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accountType_id", nullable = false)
    @NotNull(message = "Account type should not be null")
    private AccountType accountType;

    @Column(name = "a_balance", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Balance should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Balance must be greater than zero")
    private BigDecimal balance;

    @Column(name = "a_currency", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Currency")
    @Size(min = 3, max = 200, message = "Currency must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String currency;

    @Column(name = "a_created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @Column(name = "a_last_updated")
    private LocalDate lastUpdated;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accountStatus_id", nullable = false)
    @NotNull(message = "Account status should not be null")
    private AccountStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User ownerAccount;

    @Column(name = "a_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @Column(name = "a_opening_date", nullable = false)
    @PastOrPresent(message = "Invalid Opening Date")
    @NotNull(message = "Opening date should not be null")
    private LocalDate openingDate;

    @Transient
    private String faOpeningDate;

    @Column(name = "a_interest_rate", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Interest rate should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Interest rate must be greater than zero")
    private BigDecimal interestRate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "account")
    private List<Transaction> transactions;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDate.now();
    }

    public String getFaOpeningDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(openingDate)));
    }

    public void setFaOpeningDate(String faOpeningDate) {
        this.openingDate = LocalDate.from(PersianDate.parse(faOpeningDate).toGregorian().atStartOfDay());
    }
}
