package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.BudgetStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BudgetStatusService {

    BudgetStatus save(BudgetStatus budgetStatus);

    BudgetStatus edit(Long id, BudgetStatus budgetStatus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<BudgetStatus> findAll();

    BudgetStatus findById(Long id);
}