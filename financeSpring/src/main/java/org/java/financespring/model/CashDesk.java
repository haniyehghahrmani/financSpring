package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "CashDeskEntity")
@Table(name = "cash_desks")
@Cacheable
public class CashDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c_name", nullable = false, length = 100)
    @NotBlank(message = "Cash desk name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Column(name = "c_location", length = 200)
    @Size(max = 200, message = "Location must be at most 200 characters")
    private String location;

    @Column(name = "c_manager_name", length = 100)
    @Size(max = 100, message = "Manager name must be at most 100 characters")
    private String managerName;

    @Column(name = "c_contact_number", length = 50)
    @Size(max = 50, message = "Contact number must be at most 50 characters")
    private String contactNumber;

    @Column(name = "c_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "c_last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "c_active")
    private Boolean active = true;
}
