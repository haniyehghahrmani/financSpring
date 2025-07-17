package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.PayrollSetting;
import org.java.financespring.service.PayrollSettingService;
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
@RequestMapping("/payrollSetting")
public class PayrollSettingController {
    
    private final PayrollSettingService service;

    public PayrollSettingController(PayrollSettingService service) {
        this.service = service;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST)
    public PayrollSetting save(@Valid PayrollSetting payrollSetting, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(payrollSetting);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PayrollSetting edit(@Valid PayrollSetting payrollSetting, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(payrollSetting.getId(),payrollSetting);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public PayrollSetting remove(@PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<PayrollSetting> findById(@PathVariable Long id) throws NoContentException {
        return service.findPayrollSettingByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PayrollSetting> findAll(Model model) {
        return service.findAll();
    }
}
