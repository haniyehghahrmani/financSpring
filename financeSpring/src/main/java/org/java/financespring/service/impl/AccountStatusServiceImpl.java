package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.AccountStatus;
import org.java.financespring.repository.pgrepo.AccountStatusRepository;
import org.java.financespring.service.AccountStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    private final AccountStatusRepository repository;

    public AccountStatusServiceImpl(AccountStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountStatus save(AccountStatus accountStatus) {
        return repository.save(accountStatus);
    }

    @Override
    public AccountStatus edit(Long id, AccountStatus accountStatus) throws NoContentException {
        AccountStatus existingAccountStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Account Status Was Found with id " + id + " To Update!")
                );
        existingAccountStatus.setName(accountStatus.getName());

        return repository.saveAndFlush(existingAccountStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AccountStatus logicalRemove(Long id) throws NoContentException {
        repository.findAccountStatusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Account Status Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<AccountStatus> findAccountStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<AccountStatus> optional = repository.findAccountStatusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Account Status Was Found with id : " + id);
        }
    }

    @Override
    public List<AccountStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountStatus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No account status found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
