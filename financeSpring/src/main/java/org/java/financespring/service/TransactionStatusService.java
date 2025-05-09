package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionStatusService {

    TransactionStatus save(TransactionStatus transactionStatus);

    TransactionStatus edit(Long id, TransactionStatus transactionStatus);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<TransactionStatus> findAll();

    TransactionStatus findById(Long id);
}
