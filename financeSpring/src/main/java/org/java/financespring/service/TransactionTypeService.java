package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.TransactionType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransactionTypeService {

    TransactionType save(TransactionType transactionType);

    TransactionType edit(Long id, TransactionType transactionType) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<TransactionType> findTransactionTypeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<TransactionType> findAll();

    TransactionType findById(Long id) throws NoContentException;
}
