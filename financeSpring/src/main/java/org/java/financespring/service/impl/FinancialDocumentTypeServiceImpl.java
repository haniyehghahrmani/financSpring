package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocumentType;
import org.java.financespring.repository.FinancialDocumentTypeRepository;
import org.java.financespring.service.FinancialDocumentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDocumentTypeServiceImpl implements FinancialDocumentTypeService {
    
    private final FinancialDocumentTypeRepository repository;

    public FinancialDocumentTypeServiceImpl(FinancialDocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialDocumentType save(FinancialDocumentType financialDocumentType) {
        return repository.save(financialDocumentType);
    }

    @Override
    public FinancialDocumentType edit(Long id, FinancialDocumentType financialDocumentType) throws NoContentException {
        FinancialDocumentType existingFinancialDocumentType = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active FinancialDocumentType Was Found with id " + id + " To Update!")
                );
        existingFinancialDocumentType.setName(financialDocumentType.getName());

        return repository.saveAndFlush(existingFinancialDocumentType);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findFinancialDocumentTypeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active FinancialDocumentType Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<FinancialDocumentType> findFinancialDocumentTypeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<FinancialDocumentType> optional = repository.findFinancialDocumentTypeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active FinancialDocumentType Was Found with id : " + id);
        }
    }

    @Override
    public List<FinancialDocumentType> findAll() {
        return repository.findAll();
    }

    @Override
    public FinancialDocumentType findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No financialDocumentType found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
