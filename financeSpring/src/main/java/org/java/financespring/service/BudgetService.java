package org.java.financespring.service;

import org.java.financespring.model.Budget;

import java.util.List;

public interface BudgetService {

    Budget save(Budget budget);

    Budget edit(Long id, Budget budget);

    void remove(Long id);

    List<Budget> findAll();

    Budget findById(Long id);
}
