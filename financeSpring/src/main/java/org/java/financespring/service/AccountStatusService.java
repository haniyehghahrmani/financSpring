package org.java.financespring.service;

import org.java.financespring.model.AccountStatus;

import java.util.List;

public interface AccountStatusService {

    AccountStatus save(AccountStatus accountStatus);

    AccountStatus edit(Long id, AccountStatus accountStatus);

    void remove(Long id);

    List<AccountStatus> findAll();

    AccountStatus findById(Long id);
}