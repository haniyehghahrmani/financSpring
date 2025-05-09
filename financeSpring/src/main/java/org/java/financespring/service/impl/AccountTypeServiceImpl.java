package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.AccountType;
import org.java.financespring.repository.AccountTypeRepository;
import org.java.financespring.service.AccountTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

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
