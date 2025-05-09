package org.java.financespring.service;

import org.java.financespring.model.TransactionStatus;

import java.util.List;

public interface TransactionStatusService {

    TransactionStatus save(TransactionStatus transactionStatus);

    TransactionStatus edit(Long id, TransactionStatus transactionStatus);

    void remove(Long id);

    List<TransactionStatus> findAll();

    TransactionStatus findById(Long id);
}
