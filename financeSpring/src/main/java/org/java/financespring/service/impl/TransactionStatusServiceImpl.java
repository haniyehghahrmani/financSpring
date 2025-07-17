package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.TransactionStatus;
import org.java.financespring.repository.pgrepo.TransactionStatusRepository;
import org.java.financespring.service.TransactionStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionStatusServiceImpl implements TransactionStatusService {

    private final TransactionStatusRepository repository;

    public TransactionStatusServiceImpl(TransactionStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionStatus save(TransactionStatus status) {
        return repository.save(status);
    }

    @Override
    public TransactionStatus edit(Long id, TransactionStatus status) throws NoContentException {
        TransactionStatus existing = repository.findById(id)
                .orElseThrow(() -> new NoContentException("No TransactionStatus Found with id " + id + " To Update!"));

        existing.setName(status.getName());
        existing.setIsActive(status.getIsActive());

        return repository.saveAndFlush(existing);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        TransactionStatus transactionStatus = repository.findTransactionStatusByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NoContentException("No Active Transaction Status Found with id " + id + " To Remove!"));
        transactionStatus.setIsActive(false);
        repository.save(transactionStatus);
    }

    @Override
    public Optional<TransactionStatus> findTransactionStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        return Optional.empty();
    }

    @Override
    public List<TransactionStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public TransactionStatus findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("No TransactionStatus Found with id " + id));
    }
}
