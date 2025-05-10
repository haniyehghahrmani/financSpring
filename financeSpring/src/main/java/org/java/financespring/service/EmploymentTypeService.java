package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.EmploymentType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmploymentTypeService {

    EmploymentType save(EmploymentType employmentType);

    EmploymentType edit(Long id, EmploymentType employmentType);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<EmploymentType> findEmploymentTypeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<EmploymentType> findAll();

    EmploymentType findById(Long id);
}
