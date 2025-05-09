package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Deduction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeductionService {

    Deduction save(Deduction deduction);

    Deduction edit(Long id, Deduction deduction);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Deduction> findAll();

    Deduction findById(Long id);
}
