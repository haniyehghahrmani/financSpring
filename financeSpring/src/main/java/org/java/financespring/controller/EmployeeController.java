package org.java.financespring.controller;

import jakarta.persistence.Cacheable;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Employee;
import org.java.financespring.service.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Cacheable
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService service;
    
    private final EmploymentTypeService employmentTypeService;
    
    private final SalaryStructureService salaryStructureService;
    
    private final AttendanceService attendanceService;
    
    private final LeaveRequestService leaveRequestService;
    
    private final BonusService bonusService;
    
    private final DeductionService deductionService;
    
    private final PayrollService payrollService;

    public EmployeeController(EmployeeService service, EmploymentTypeService employmentTypeService, SalaryStructureService salaryStructureService, AttendanceService attendanceService, LeaveRequestService leaveRequestService, BonusService bonusService, DeductionService deductionService, PayrollService payrollService) {
        this.service = service;
        this.employmentTypeService = employmentTypeService;
        this.salaryStructureService = salaryStructureService;
        this.attendanceService = attendanceService;
        this.leaveRequestService = leaveRequestService;
        this.bonusService = bonusService;
        this.deductionService = deductionService;
        this.payrollService = payrollService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST)
    public Employee save(@Valid Employee employee, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee edit(@Valid Employee employee, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(employee.getId(),employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public Employee remove(@PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Employee> findById(@PathVariable Long id) throws NoContentException {
        return service.findEmployeeByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Employee> findAll(Model model) {
        return service.findAll();
    }
}
