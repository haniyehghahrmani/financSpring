package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.AccountType;
import org.java.financespring.repository.h2repo.AccountTypeRepository;
import org.java.financespring.service.AccountTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository repository;

    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountType save(AccountType accountType) {
        return repository.save(accountType);
    }

    @Override
    public AccountType edit(Long id, AccountType accountType) throws NoContentException {
        AccountType existingAccountType = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Account Type Was Found with id " + id + " To Update!")
                );
        existingAccountType.setName(accountType.getName());

        return repository.saveAndFlush(existingAccountType);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AccountType logicalRemove(Long id) throws NoContentException {
        repository.findAccountTypeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Account Type Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<AccountType> findAccountTypeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<AccountType> optional = repository.findAccountTypeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Account Type Was Found with id : " + id);
        }
    }

    @Override
    public List<AccountType> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountType findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No account type found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
