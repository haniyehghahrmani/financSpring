package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Deduction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DeductionService {

    Deduction save(Deduction deduction);

    Deduction edit(Long id, Deduction deduction) throws NoContentException;

    void remove(Long id);

    @Transactional
    Deduction logicalRemove(Long id) throws NoContentException;

    Optional<Deduction> findDeductionByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Deduction> findAll();

    Deduction findById(Long id);
}
