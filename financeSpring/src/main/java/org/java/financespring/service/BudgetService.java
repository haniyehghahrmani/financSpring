package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Budget;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BudgetService {

    Budget save(Budget budget);

    Budget edit(Long id, Budget budget) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Budget> findAll();

    Budget findById(Long id);
}
