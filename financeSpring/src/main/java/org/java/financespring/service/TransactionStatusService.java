package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransactionStatusService {

    TransactionStatus save(TransactionStatus transactionStatus);

    TransactionStatus edit(Long id, TransactionStatus transactionStatus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<TransactionStatus> findTransactionStatusByIdAndDeletedFalse(Long id) throws NoContentException;

    List<TransactionStatus> findAll();

    TransactionStatus findById(Long id) throws NoContentException;
}
