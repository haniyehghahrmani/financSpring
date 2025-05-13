package org.java.financespring.controller;

import jakarta.persistence.Cacheable;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Deduction;
import org.java.financespring.service.DeductionService;
import org.java.financespring.service.EmployeeService;
import org.java.financespring.service.PayrollService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Cacheable
@RequestMapping("/deduction")
public class DeductionController {

    private final DeductionService service;

    private final EmployeeService employeeService;

    private final PayrollService payrollService;

    public DeductionController(DeductionService service, EmployeeService employeeService, PayrollService payrollService) {
        this.service = service;
        this.employeeService = employeeService;
        this.payrollService = payrollService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST)
    public Deduction save(@Valid Deduction deduction, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(deduction);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Deduction edit(@Valid Deduction deduction, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(deduction.getId(), deduction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public Deduction remove(@PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Deduction> findById(@PathVariable Long id) throws NoContentException {
        return service.findDeductionByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Deduction> findAll(Model model) {
        return service.findAll();
    }
}
