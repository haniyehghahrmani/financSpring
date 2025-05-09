package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.AccountStatus;

import java.util.List;

public interface AccountStatusService {

    AccountStatus save(AccountStatus accountStatus);

    AccountStatus edit(Long id, AccountStatus accountStatus) throws NoContentException;

    void remove(Long id);

    List<AccountStatus> findAll();

    AccountStatus findById(Long id);
}