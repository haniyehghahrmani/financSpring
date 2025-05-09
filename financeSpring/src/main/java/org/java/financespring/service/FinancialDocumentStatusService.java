package org.java.financespring.service;

import org.java.financespring.model.FinancialDocumentStatus;

import java.util.List;

public interface FinancialDocumentStatusService {

    FinancialDocumentStatus save(FinancialDocumentStatus financialDocumentStatus);

    FinancialDocumentStatus edit(Long id, FinancialDocumentStatus financialDocumentStatus);

    void remove(Long id);

    List<FinancialDocumentStatus> findAll();

    FinancialDocumentStatus findById(Long id);
}
