package org.java.financespring.service;

import org.java.financespring.model.Payroll;

import java.util.List;

public interface PayrollService {

    Payroll save(Payroll payroll);

    Payroll edit(Long id, Payroll payroll);

    void remove(Long id);

    List<Payroll> findAll();

    Payroll findById(Long id);
}
