package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Deduction;
import org.java.financespring.repository.DeductionRepository;
import org.java.financespring.service.DeductionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeductionServiceImpl implements DeductionService {
    
    private final DeductionRepository repository;

    public DeductionServiceImpl(DeductionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Deduction save(Deduction deduction) {
        return repository.save(deduction);
    }

    @Override
    public Deduction edit(Long id, Deduction deduction) throws NoContentException {
        Deduction existingDeduction = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Deduction Was Found with id " + id + " To Update!")
                );

        existingDeduction.setEmployee(deduction.getEmployee());
        existingDeduction.setPayroll(deduction.getPayroll());
        existingDeduction.setAmount(deduction.getAmount());
        existingDeduction.setReason(deduction.getReason());
        existingDeduction.setDeductionDate(deduction.getDeductionDate());
        existingDeduction.setRecurring(deduction.isRecurring());

        return repository.saveAndFlush(existingDeduction);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findDeductionByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Deduction Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Deduction> findDeductionByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Deduction> optional = repository.findDeductionByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Deduction Was Found with id : " + id);
        }
    }

    @Override
    public List<Deduction> findAll() {
        return repository.findAll();
    }

    @Override
    public Deduction findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No deduction found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
