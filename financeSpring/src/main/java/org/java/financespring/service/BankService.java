package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Bank;

import java.util.List;

public interface BankService {

    Bank save(Bank bank);

    Bank edit(Long id, Bank bank) throws NoContentException;

    void remove(Long id);

    List<Bank> findAll();

    Bank findById(Long id);
}