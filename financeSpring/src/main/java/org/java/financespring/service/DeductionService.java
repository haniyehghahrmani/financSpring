package org.java.financespring.service;

import org.java.financespring.model.Deduction;

import java.util.List;

public interface DeductionService {

    Deduction save(Deduction deduction);

    Deduction edit(Long id, Deduction deduction);

    void remove(Long id);

    List<Deduction> findAll();

    Deduction findById(Long id);
}
