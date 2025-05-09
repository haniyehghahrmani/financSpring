package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    Account edit(Long id, Account account) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<Account> findAccountByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Account> findAll();

    Account findById(Long id);
}