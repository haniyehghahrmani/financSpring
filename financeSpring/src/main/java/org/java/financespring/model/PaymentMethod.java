package org.java.financespring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PaymentMethod {

    @Id
    private Long id;
}
