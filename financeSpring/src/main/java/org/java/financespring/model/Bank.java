package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "BankEntity")
@Table(name = "banks")
@Cacheable
public class Bank {

    @Id
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    @Column(name = "b_id")
    private Long bankID;

    @Column(name = "b_name", length = 200, nullable = false)
    @NotBlank(message = "Bank name should not be blank")
    @Size(min = 2, max = 200, message = "Bank name must be between 2 and 200 characters")
    private String bankName;

    @Column(name = "b_swift_code", length = 20, nullable = false)
    @NotBlank(message = "SWIFT code should not be blank")
    @Size(max = 20, message = "SWIFT code must be at most 20 characters")
    private String swiftCode;

    @Column(name = "b_iban", length = 34)
    @Size(max = 34, message = "IBAN must be at most 34 characters")
    private String iban;

    @Column(name = "b_routing_number", length = 20)
    @Size(max = 20, message = "Routing number must be at most 20 characters")
    private String routingNumber;

    @Column(name = "b_address", length = 300)
    @Size(max = 300, message = "Address must be at most 300 characters")
    private String address;

    @Column(name = "b_contact_number", length = 20)
    @Size(max = 20, message = "Contact number must be at most 20 characters")
    private String contactNumber;

    @Column(name = "b_website", length = 200)
    @Size(max = 200, message = "Website must be at most 200 characters")
    private String website;

    @Column(name = "b_country", length = 100)
    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @Column(name = "b_created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "b_last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Date();
    }

    @Column(name = "is_active")
    private Boolean isActive = true;
}