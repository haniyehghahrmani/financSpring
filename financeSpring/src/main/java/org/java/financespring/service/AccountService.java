package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    Account edit(Long id, Account account) throws NoContentException;

    void remove(Long id);

    List<Account> findAll();

    Account findById(Long id);
}