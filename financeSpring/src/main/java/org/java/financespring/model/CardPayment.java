package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "CardPaymentEntity")
@Table(name = "card_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CardPayment extends Payment {

    @Column(name = "card_number", length = 20, nullable = false)
    @NotBlank
    @Size(max = 20)
    private String cardNumber;

    @Column(name = "card_holder_name", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String cardHolderName;

    @Column(name = "expiry_date", length = 7, nullable = false)
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{4}$")
    private String expiryDate;

    @Column(name = "cvv", length = 4, nullable = false)
    @NotBlank
    @Pattern(regexp = "^\\d{3,4}$")
    private String cvv;

    @Column(name = "bank_id", nullable = false)
    @NotNull
    private Integer bankID;
}
