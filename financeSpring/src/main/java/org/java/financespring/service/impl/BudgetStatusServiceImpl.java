package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.BudgetStatus;
import org.java.financespring.repository.BudgetStatusRepository;
import org.java.financespring.service.BudgetStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetStatusServiceImpl implements BudgetStatusService {

    private final BudgetStatusRepository repository;

    public BudgetStatusServiceImpl(BudgetStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public BudgetStatus save(BudgetStatus budgetStatus) {
        return repository.save(budgetStatus);
    }

    @Override
    public BudgetStatus edit(Long id, BudgetStatus budgetStatus) throws NoContentException {
        BudgetStatus existingBudgetStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Budget status Was Found with id " + id + " To Update!")
                );
        existingBudgetStatus.setName(budgetStatus.getName());

        return repository.saveAndFlush(existingBudgetStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BudgetStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public BudgetStatus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No budget status found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
