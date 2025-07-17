package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Payroll;
import org.java.financespring.service.EmployeeService;
import org.java.financespring.service.PayrollService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

    private final PayrollService service;

    private final EmployeeService employeeService;

    public PayrollController(PayrollService service, EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST)
    public Payroll save(@Valid Payroll payroll, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(payroll);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Payroll edit(@Valid Payroll payroll, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(payroll.getId(), payroll);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public Payroll remove(@PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Payroll> findById(@PathVariable Long id) throws NoContentException {
        return service.findPayrollByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Payroll> findAll(Model model) {
        return service.findAll();
    }
}
