package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Bank;
import org.java.financespring.service.BankService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping
    public String bankForm(Model model) {
        model.addAttribute("bankList", service.findAll());
        model.addAttribute("bank", new Bank());
        return "bank";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Bank save(@Valid Bank bank, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(bank);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Bank update(@PathVariable Long id, @Valid Bank bank, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(id, bank);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void remove(@PathVariable Long id) throws NoContentException {
        service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Bank findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bank> findAll() {
        return service.findAll();
    }
}
