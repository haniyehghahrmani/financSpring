package org.java.financespring.service;

import org.java.financespring.model.Bank;

import java.util.List;

public interface BankService {

    Bank save(Bank bank);

    Bank edit(Long id, Bank bank);

    void remove(Long id);

    List<Bank> findAll();

    Bank findById(Long id);
}