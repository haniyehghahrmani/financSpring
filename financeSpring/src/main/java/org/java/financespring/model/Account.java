package org.java.financespring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "account")
    private List<Transaction> transactions;
}
