package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.AccountType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {

    AccountType save(AccountType accountType);

    AccountType edit(Long id, AccountType accountType) throws NoContentException;

    void remove(Long id);

    @Transactional
    AccountType logicalRemove(Long id) throws NoContentException;

    Optional<AccountType> findAccountTypeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<AccountType> findAll();

    AccountType findById(Long id);
}