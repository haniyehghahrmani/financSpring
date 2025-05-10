package org.java.financespring.service.impl;

import jakarta.transaction.Transactional;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Budget;
import org.java.financespring.repository.BudgetRepository;
import org.java.financespring.service.BudgetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository repository;

    public BudgetServiceImpl(BudgetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Budget save(Budget budget) {
        return repository.save(budget);
    }

    @Override
    public Budget edit(Long id, Budget budget) throws NoContentException {
        Budget existingBudget = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Budget Was Found with id " + id + " To Update!")
                );
        existingBudget.setBudgetName(budget.getBudgetName());
        existingBudget.setTotalAmount(budget.getTotalAmount());
        existingBudget.setSpentAmount(budget.getSpentAmount());
        existingBudget.setRemainingAmount(budget.getRemainingAmount());
        existingBudget.setStartDate(budget.getStartDate());
        existingBudget.setEndDate(budget.getEndDate());
        existingBudget.setStatus(budget.getStatus());
        existingBudget.setOwner(budget.getOwner());
        existingBudget.setDescription(budget.getDescription());
        existingBudget.setTransactions(budget.getTransactions());

        return repository.saveAndFlush(existingBudget);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findBudgetByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Budget Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Budget> findBudgetByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Budget> optional = repository.findBudgetByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Attachment Was Found with id : " + id);
        }
    }

    @Override
    public List<Budget> findAll() {
        return repository.findAll();
    }

    @Override
    public Budget findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No budget found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
