package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocument;
import org.java.financespring.repository.FinancialDocumentRepository;
import org.java.financespring.service.FinancialDocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDocumentServiceImpl implements FinancialDocumentService {
    
    private final FinancialDocumentRepository repository;

    public FinancialDocumentServiceImpl(FinancialDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialDocument save(FinancialDocument financialDocument) {
        return repository.save(financialDocument);
    }

    @Override
    public FinancialDocument edit(Long id, FinancialDocument financialDocument) throws NoContentException {
        FinancialDocument existingFinancialDocument = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active FinancialDocument Was Found with id " + id + " To Update!")
                );
//        existingFinancialDocument.setAttachment(financialDocument.getAttachment());
        existingFinancialDocument.setDocumentType(financialDocument.getDocumentType());
        existingFinancialDocument.setDocumentNumber(financialDocument.getDocumentNumber());
        existingFinancialDocument.setDocumentDate(financialDocument.getDocumentDate());
        existingFinancialDocument.setTotalAmount(financialDocument.getTotalAmount());
        existingFinancialDocument.setPaidAmount(financialDocument.getPaidAmount());
        existingFinancialDocument.setStatus(financialDocument.getStatus());
        existingFinancialDocument.setDescription(financialDocument.getDescription());
        existingFinancialDocument.setCreatedBy(financialDocument.getCreatedBy());

        return repository.saveAndFlush(existingFinancialDocument);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findFinancialDocumentByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active FinancialDocument Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<FinancialDocument> optional = repository.findFinancialDocumentByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active FinancialDocument Was Found with id : " + id);
        }
    }

    @Override
    public List<FinancialDocument> findAll() {
        return repository.findAll();
    }

    @Override
    public FinancialDocument findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No financialDocument found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
