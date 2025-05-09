package org.java.financespring.service;

import org.java.financespring.model.EmploymentType;

import java.util.List;

public interface EmploymentTypeService {

    EmploymentType save(EmploymentType employmentType);

    EmploymentType edit(Long id, EmploymentType employmentType);

    void remove(Long id);

    List<EmploymentType> findAll();

    EmploymentType findById(Long id);
}
