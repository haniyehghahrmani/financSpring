package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "CheckPaymentEntity")
@Table(name = "check_payments")
@Cacheable
public class CheckPayment extends PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private Long id;

    @Column(name = "c_number", length = 50, nullable = false)
    @NotBlank(message = "Check number is required")
    @Size(max = 50, message = "Check number must be at most 50 characters")
    private String chequeNumber;

    @OneToMany
    private Account account;

    @Column(name = "c_issue_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Issue date is required")
    private Date issueDate;

    @Column(name = "c_is_active")
    private Boolean isActive = true;
}
