package org.java.financespring.service;

import org.java.financespring.model.FinancialDocument;

import java.util.List;

public interface FinancialDocumentService {

    FinancialDocument save(FinancialDocument financialDocument);

    FinancialDocument edit(Long id, FinancialDocument financialDocument);

    void remove(Long id);

    List<FinancialDocument> findAll();

    FinancialDocument findById(Long id);
}
