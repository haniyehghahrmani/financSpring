package org.java.financespring.service;

import org.java.financespring.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee edit(Long id, Employee employee);

    void remove(Long id);

    List<Employee> findAll();

    Employee findById(Long id);
}
