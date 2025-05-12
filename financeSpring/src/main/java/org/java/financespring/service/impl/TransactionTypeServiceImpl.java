package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.TransactionStatus;
import org.java.financespring.model.TransactionType;
import org.java.financespring.repository.TransactionTypeRepository;
import org.java.financespring.service.TransactionTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository repository;

    public TransactionTypeServiceImpl(TransactionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionType save(TransactionType type) {
        return repository.save(type);
    }

    @Override
    public TransactionType edit(Long id, TransactionType type) throws NoContentException {
        TransactionType existing = repository.findById(id)
                .orElseThrow(() -> new NoContentException("No TransactionType Found with id " + id + " To Update!"));

        existing.setName(type.getName());
        existing.setIsActive(type.getIsActive());

        return repository.saveAndFlush(existing);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        TransactionType transactionType = repository.findTransactionTypeByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NoContentException("No Active Transaction Type Found with id " + id + " To Remove!"));
        transactionType.setIsActive(false);
        repository.save(transactionType);
    }

    @Override
    public Optional<TransactionType> findTransactionTypeByIdAndDeletedFalse(Long id) throws NoContentException {
        return Optional.empty();
    }

    @Override
    public List<TransactionType> findAll() {
        return repository.findAll();
    }

    @Override
    public TransactionType findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("No TransactionType Found with id " + id));
    }
}
