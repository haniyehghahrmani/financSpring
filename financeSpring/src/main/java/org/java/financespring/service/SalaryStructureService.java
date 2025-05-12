package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.SalaryStructure;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SalaryStructureService {

    SalaryStructure save(SalaryStructure salaryStructure);

    SalaryStructure edit(Long id, SalaryStructure salaryStructure) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<SalaryStructure> findSalaryStructureByIdAndDeletedFalse(Long id) throws NoContentException;

    List<SalaryStructure> findAll();

    SalaryStructure findById(Long id);
}
