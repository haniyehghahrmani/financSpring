package org.java.financespring.service;

import org.java.financespring.model.SalaryStructure;

import java.util.List;

public interface SalaryStructureService {

    SalaryStructure save(SalaryStructure salaryStructure);

    SalaryStructure edit(Long id, SalaryStructure salaryStructure);

    void remove(Long id);

    List<SalaryStructure> findAll();

    SalaryStructure findById(Long id);
}
