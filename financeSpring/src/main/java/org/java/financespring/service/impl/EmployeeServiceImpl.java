package org.java.financespring.service.impl;

import org.java.financespring.dto.EmployeeDTO;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.mapper.EmployeeMapper;
import org.java.financespring.model.Employee;
import org.java.financespring.repository.EmployeeRepository;
import org.java.financespring.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper employeeMapper) {
        this.repository = repository;
        this.employeeMapper = employeeMapper;
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
        existingEmployee.setPerson(employee.getPerson());
        existingEmployee.setEmployeeCode(employee.getEmployeeCode());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setBaseSalary(employee.getBaseSalary());
        existingEmployee.setAccount(employee.getAccount());
        existingEmployee.setInsuranceNumber(employee.getInsuranceNumber());
        existingEmployee.setActive(employee.isActive());
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
    public Employee logicalRemove(Long id) throws NoContentException {
        repository.findEmployeeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Employee Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
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
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = repository.findAll();
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> findById(Long id) {
        return repository.findById(id)
                .map(employeeMapper::toDTO);
    }
}