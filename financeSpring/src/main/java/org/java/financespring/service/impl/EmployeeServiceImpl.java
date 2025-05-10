package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Employee;
import org.java.financespring.repository.EmployeeRepository;
import org.java.financespring.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee edit(Long id, Employee employee) throws NoContentException {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Employee Was Found with id " + id + " To Update!")
                );
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmployeeCode(employee.getEmployeeCode());
        existingEmployee.setNationalId(employee.getNationalId());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setBaseSalary(employee.getBaseSalary());
        existingEmployee.setBankName(employee.getBankName());
        existingEmployee.setBankAccountNumber(employee.getBankAccountNumber());
        existingEmployee.setShebaNumber(employee.getShebaNumber());
        existingEmployee.setInsuranceNumber(employee.getInsuranceNumber());
        existingEmployee.setActive(employee.isActive());
        existingEmployee.setBirthDate(employee.getBirthDate());
        existingEmployee.setEmploymentType(employee.getEmploymentType());
        existingEmployee.setSalaryStructure(employee.getSalaryStructure());
        existingEmployee.setAttendance(employee.getAttendance());
        existingEmployee.setLeaveRequest(employee.getLeaveRequest());
        existingEmployee.setBonus(employee.getBonus());
        existingEmployee.setDeduction(employee.getDeduction());
        existingEmployee.setPayroll(employee.getPayroll());

        return repository.saveAndFlush(existingEmployee);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findEmployeeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Employee Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Employee> findEmployeeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Employee> optional = repository.findEmployeeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Employee Was Found with id : " + id);
        }
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No employee found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}