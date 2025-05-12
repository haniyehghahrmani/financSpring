package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Transaction;
import org.java.financespring.repository.TransactionRepository;
import org.java.financespring.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction edit(Long id, Transaction transaction) throws NoContentException {
        Transaction existing = repository.findById(id)
                .orElseThrow(() -> new NoContentException("No Active Transaction Found with id " + id + " To Update!"));

        existing.setTransactionDate(transaction.getTransactionDate());
        existing.setTransactionTime(transaction.getTransactionTime());
        existing.setAmount(transaction.getAmount());
        existing.setDescription(transaction.getDescription());
        existing.setType(transaction.getType());
        existing.setAccount(transaction.getAccount());
        existing.setBudget(transaction.getBudget());
        existing.setStatus(transaction.getStatus());
        existing.setUser(transaction.getUser());
        existing.setIsActive(transaction.getIsActive());

        return repository.saveAndFlush(existing);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        Transaction transaction = repository.findTransactionByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NoContentException("No Active Transaction Found with id " + id + " To Remove!"));
        transaction.setIsActive(false);
        repository.save(transaction);
    }

    @Override
    public Optional<Transaction> findTransactionByIdAndDeletedFalse(Long id) throws NoContentException {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Transaction findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("No Transaction Found with id " + id));
    }
}
