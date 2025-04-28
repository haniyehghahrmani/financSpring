package org.java.financespring.model;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}