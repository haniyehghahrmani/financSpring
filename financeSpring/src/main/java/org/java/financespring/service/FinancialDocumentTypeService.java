package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.FinancialDocumentType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FinancialDocumentTypeService {

    FinancialDocumentType save(FinancialDocumentType financialDocumentType);

    FinancialDocumentType edit(Long id, FinancialDocumentType financialDocumentType);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<FinancialDocumentType> findAll();

    FinancialDocumentType findById(Long id);
}
