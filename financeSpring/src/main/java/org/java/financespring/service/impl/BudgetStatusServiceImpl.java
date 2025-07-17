package org.java.financespring.service.impl;

import jakarta.transaction.Transactional;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.BudgetStatus;
import org.java.financespring.repository.h2repo.BudgetStatusRepository;
import org.java.financespring.service.BudgetStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public BudgetStatus logicalRemove(Long id) throws NoContentException {
        repository.findBudgetStatusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Budget Status Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<BudgetStatus> findBudgetStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<BudgetStatus> optional = repository.findBudgetStatusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Budget Status Was Found with id : " + id);
        }
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
