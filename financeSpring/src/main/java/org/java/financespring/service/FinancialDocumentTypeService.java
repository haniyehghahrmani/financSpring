package org.java.financespring.service;

import org.java.financespring.model.FinancialDocumentType;

import java.util.List;

public interface FinancialDocumentTypeService {

    FinancialDocumentType save(FinancialDocumentType financialDocumentType);

    FinancialDocumentType edit(Long id, FinancialDocumentType financialDocumentType);

    void remove(Long id);

    List<FinancialDocumentType> findAll();

    FinancialDocumentType findById(Long id);
}
