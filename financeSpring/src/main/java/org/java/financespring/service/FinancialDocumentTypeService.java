package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.FinancialDocumentType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FinancialDocumentTypeService {

    FinancialDocumentType save(FinancialDocumentType financialDocumentType);

    FinancialDocumentType edit(Long id, FinancialDocumentType financialDocumentType) throws NoContentException;

    void remove(Long id);

    @Transactional
    FinancialDocumentType logicalRemove(Long id) throws NoContentException;

    Optional<FinancialDocumentType> findFinancialDocumentTypeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<FinancialDocumentType> findAll();

    FinancialDocumentType findById(Long id);
}
