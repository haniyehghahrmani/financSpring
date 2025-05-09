package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PaymentStatusEntity")
@Table(name = "payment_statuses")
@Cacheable
public class PaymentStatus extends Base{

    @Id
    @SequenceGenerator(name = "paymentStatusSeq", sequenceName = "payment_status_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentStatusSeq")
    @Column(name = "p_status_id")
    private Long id;

    @Column(name = "p_name", nullable = false, length = 50, unique = true)
    @NotBlank(message = "Status name is required")
    @Size(max = 50)
    private String statusName;

    @Column(name = "p_description", length = 255)
    @Size(max = 255)
    private String description;

    @Column(name = "p_is_active")
    private Boolean isActive = true;
}
