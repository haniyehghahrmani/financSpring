package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocument;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FinancialDocumentService {

    FinancialDocument save(FinancialDocument financialDocument);

    FinancialDocument edit(Long id, FinancialDocument financialDocument);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id) throws NoContentException;

    List<FinancialDocument> findAll();

    FinancialDocument findById(Long id);
}
