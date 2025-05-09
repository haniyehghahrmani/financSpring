package org.java.financespring.service.impl;

import org.java.financespring.model.Account;
import org.java.financespring.repository.AccountRepository;
import org.java.financespring.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return
    }

    @Override
    public Account edit(Long id, Account account) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public Account findById(Long id) {
        return null;
    }
}
