package org.java.financespring.service;

import org.java.financespring.model.BudgetStatus;

import java.util.List;

public interface BudgetStatusService {

    BudgetStatus save(BudgetStatus budgetStatus);

    BudgetStatus edit(Long id, BudgetStatus budgetStatus);

    void remove(Long id);

    List<BudgetStatus> findAll();

    BudgetStatus findById(Long id);
}