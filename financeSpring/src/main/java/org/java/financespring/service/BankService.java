package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Bank;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankService {

    Bank save(Bank bank);

    Bank edit(Long id, Bank bank) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Bank> findAll();

    Bank findById(Long id);
}