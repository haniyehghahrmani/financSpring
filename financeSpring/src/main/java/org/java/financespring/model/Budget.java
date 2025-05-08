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

@Entity(name = "BudgetEntity")
@Table(name = "BudgetTbl")
public class Budget {

    @Id
    @SequenceGenerator(name = "budgetSeq", sequenceName = "budget_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budgetSeq")
    @Column(name = "budget_id")
    private Long id;

    @Column(name = "budget_name", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid name")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String budgetName;

    //مقدار کل
    @Column(name = "budget_total_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal totalAmount;

    //مقدار خرج‌شده
    @Column(name = "budget_spent_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal spentAmount;

    //باقیمانده‌ی بودجه
    @Column(name = "budget_remaining_amount", precision = 18, scale = 2, nullable = false)
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal remainingAmount;

    @Column(name = "budget_start_date", nullable = false)
    @PastOrPresent(message = "Invalid Start Date")
    @NotNull(message = "Start date should not be null")
    private LocalDate startDate;

    @Transient
    private String faStartDate;

    @Column(name = "budget_end_date", nullable = false)
    @PastOrPresent(message = "Invalid End Date")
    @NotNull(message = "End date should not be null")
    private LocalDate endDate;

    @Transient
    private String faEndDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "budget_budget_status", nullable = false)
    private BudgetStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "budget_owner", nullable = false)
    private User owner;

    @Column(name = "budget_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @Column(name = "budget_created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "budget_last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

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