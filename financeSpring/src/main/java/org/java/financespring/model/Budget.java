package org.java.financespring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "BudgetEntity")
@Table(name = "budgets")
public class Budget extends Base{

    @Id
    @SequenceGenerator(name = "budgetSeq", sequenceName = "budget_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetSeq")
    @Column(name = "b_id")
    private Long id;

    @Column(name = "b_name", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid name")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String budgetName;

    //مقدار کل
    @Column(name = "b_total_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal totalAmount;

    //مقدار خرج‌شده
    @Column(name = "b_spent_amount", precision = 18, scale = 2)
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal spentAmount;

    //باقیمانده‌ی بودجه
    @Column(name = "b_remaining_amount", precision = 18, scale = 2)
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal remainingAmount;

    @Column(name = "b_start_date", nullable = false)
    @PastOrPresent(message = "Invalid Start Date")
    @NotNull(message = "Start date should not be null")
    private LocalDate startDate;

    @Transient
    private String faStartDate;

    @Column(name = "b_end_date", nullable = false)
    @FutureOrPresent(message = "Invalid End Date")
    @NotNull(message = "End date should not be null")
    private LocalDate endDate;

    @Transient
    private String faEndDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "budget_status", nullable = false)
    private BudgetStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "budget_owner", nullable = false)
    @JsonIgnore
    private User owner;

    @JsonProperty("ownerUsername")
    private String getOwnerUsername() {
        return owner != null ? owner.getUsername() : null;
    }

    @Column(name = "b_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @Column(name = "b_created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "b_last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonIgnore
    private List<Transaction> transactions;

    @JsonProperty("transactionsId")
    private List<Long> getTransactionsId() {
        if (transactions != null && !transactions.isEmpty()) {
            return Collections.singletonList(transactions.get(0).getId());
        } else {
            return Collections.emptyList();
        }
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    public String getFaStartDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(startDate)));
    }

    public void setFaStartDate(String faStartDate) {
        this.startDate=LocalDate.from(PersianDate.parse(faStartDate).toGregorian().atStartOfDay());
    }

    public String getFaEndDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(endDate)));
    }

    public void setFaEndDate(String faEndDate) {
        this.endDate=LocalDate.from(PersianDate.parse(faEndDate).toGregorian().atStartOfDay());
    }
}