package org.java.financespring.service;

import org.java.financespring.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction save(Transaction transaction);

    Transaction edit(Long id, Transaction transaction);

    void remove(Long id);

    List<Transaction> findAll();

    Transaction findById(Long id);
}
