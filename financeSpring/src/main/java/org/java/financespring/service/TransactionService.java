package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {

    Transaction save(Transaction transaction);

    Transaction edit(Long id, Transaction transaction);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Transaction> findAll();

    Transaction findById(Long id);
}
