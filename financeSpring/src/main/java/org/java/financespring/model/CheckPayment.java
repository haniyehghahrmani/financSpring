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
    private Long id;

    @Column(name = "check_number", length = 50, nullable = false)
    @NotBlank(message = "Check number is required")
    @Size(max = 50, message = "Check number must be at most 50 characters")
    private String chequeNumber;

    @Column(name = "bank_name", length = 100, nullable = false)
    @NotBlank(message = "Bank name is required")
    @Size(max = 100, message = "Bank name must be at most 100 characters")
    private String bankName;

    @Column(name = "issue_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Issue date is required")
    private Date issueDate;
}
