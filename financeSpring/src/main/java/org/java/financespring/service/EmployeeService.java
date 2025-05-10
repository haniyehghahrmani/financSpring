package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee edit(Long id, Employee employee) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<Employee> findEmployeeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Employee> findAll();

    Employee findById(Long id);
}
