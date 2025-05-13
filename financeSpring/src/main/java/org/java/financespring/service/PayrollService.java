package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Payroll;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PayrollService {

    Payroll save(Payroll payroll);

    Payroll edit(Long id, Payroll payroll) throws NoContentException;

    void remove(Long id);

    @Transactional
    Payroll logicalRemove(Long id) throws NoContentException;

    Optional<Payroll> findPayrollByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Payroll> findAll();

    Payroll findById(Long id);
}
