package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Payroll;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PayrollService {

    Payroll save(Payroll payroll);

    Payroll edit(Long id, Payroll payroll);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Payroll> findAll();

    Payroll findById(Long id);
}
