package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    AccountType save(AccountType accountType);

    AccountType edit(Long id, AccountType accountType) throws NoContentException;

    void remove(Long id);

    List<AccountType> findAll();

    AccountType findById(Long id);
}