package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocumentStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FinancialDocumentStatusService {

    FinancialDocumentStatus save(FinancialDocumentStatus financialDocumentStatus);

    FinancialDocumentStatus edit(Long id, FinancialDocumentStatus financialDocumentStatus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<FinancialDocumentStatus> findFinancialDocumentStatusByIdAndDeletedFalse(Long id) throws NoContentException;

    List<FinancialDocumentStatus> findAll();

    FinancialDocumentStatus findById(Long id);
}
