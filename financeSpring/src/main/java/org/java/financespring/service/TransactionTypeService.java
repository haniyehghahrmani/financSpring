package org.java.financespring.service;

import org.java.financespring.model.TransactionType;

import java.util.List;

public interface TransactionTypeService {

    TransactionType save(TransactionType transactionType);

    TransactionType edit(Long id, TransactionType transactionType);

    void remove(Long id);

    List<TransactionType> findAll();

    TransactionType findById(Long id);
}
