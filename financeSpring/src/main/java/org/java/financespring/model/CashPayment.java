package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "CashPaymentEntity")
@Table(name = "cash_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CashPayment extends PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c-received_by", length = 100, nullable = false)
    @NotBlank(message = "ReceivedBy is required")
    @Size(max = 100)
    private String receivedBy;

    @Column(name = "c-location", length = 200)
    @Size(max = 200)
    private String location;

    @Column(name = "c-is_active")
    private Boolean isActive = true;
}


