package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Budget;

import java.util.List;

public interface BudgetService {

    Budget save(Budget budget);

    Budget edit(Long id, Budget budget) throws NoContentException;

    void remove(Long id);

    List<Budget> findAll();

    Budget findById(Long id);
}
