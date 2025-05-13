package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Account;
import org.java.financespring.repository.AccountRepository;
import org.java.financespring.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public Account edit(Long id, Account account) throws NoContentException {
        Account existingAccount = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Account Was Found with id " + id + " To Update!")
                );
        existingAccount.setAccountName(account.getAccountName());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setCurrency(account.getCurrency());
        existingAccount.setStatus(account.getStatus());
        existingAccount.setOwnerAccount(account.getOwnerAccount());
        existingAccount.setDescription(account.getDescription());
        existingAccount.setOpeningDate(account.getOpeningDate());
        existingAccount.setInterestRate(account.getInterestRate());
        existingAccount.setTransactions(account.getTransactions());

        return repository.saveAndFlush(existingAccount);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Account logicalRemove(Long id) throws NoContentException {
        repository.findAccountByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Account Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<Account> findAccountByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Account> optional = repository.findAccountByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Account Was Found with id : " + id);
        }
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No account found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
