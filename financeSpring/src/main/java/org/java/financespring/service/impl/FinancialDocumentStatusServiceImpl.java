package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocumentStatus;
import org.java.financespring.repository.FinancialDocumentStatusRepository;
import org.java.financespring.service.FinancialDocumentStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDocumentStatusServiceImpl implements FinancialDocumentStatusService {

    private final FinancialDocumentStatusRepository repository;

    public FinancialDocumentStatusServiceImpl(FinancialDocumentStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialDocumentStatus save(FinancialDocumentStatus financialDocumentStatus) {
        return repository.save(financialDocumentStatus);
    }

    @Override
    public FinancialDocumentStatus edit(Long id, FinancialDocumentStatus financialDocumentStatus) throws NoContentException {
        FinancialDocumentStatus existingFinancialDocumentStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active FinancialDocumentStatus Was Found with id " + id + " To Update!")
                );
        existingFinancialDocumentStatus.setName(financialDocumentStatus.getName());

        return repository.saveAndFlush(existingFinancialDocumentStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public FinancialDocumentStatus logicalRemove(Long id) throws NoContentException {
        repository.findFinancialDocumentStatusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active FinancialDocumentStatus Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<FinancialDocumentStatus> findFinancialDocumentStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<FinancialDocumentStatus> optional = repository.findFinancialDocumentStatusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active FinancialDocumentStatus Was Found with id : " + id);
        }
    }

    @Override
    public List<FinancialDocumentStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public FinancialDocumentStatus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No financialDocumentStatus found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
