package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Account;
import org.java.financespring.model.AccountStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountStatusService {

    AccountStatus save(AccountStatus accountStatus);

    AccountStatus edit(Long id, AccountStatus accountStatus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<AccountStatus> findAccountStatusByIdAndDeletedFalse(Long id) throws NoContentException;

    List<AccountStatus> findAll();

    AccountStatus findById(Long id);
}