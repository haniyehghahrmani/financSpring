package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.TransactionType;
import org.java.financespring.service.TransactionTypeService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transaction-types")
public class TransactionTypeController {

    private final TransactionTypeService service;

    public TransactionTypeController(TransactionTypeService service) {
        this.service = service;
    }

    @GetMapping
    public String transactionTypeForm(Model model) {
        model.addAttribute("transactionTypeList", service.findAll());
        model.addAttribute("transactionType", new TransactionType());
        return "transaction-type";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TransactionType save(@Valid TransactionType transactionType, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(transactionType);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TransactionType update(@PathVariable Long id, @Valid TransactionType transactionType, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(id, transactionType);
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
    public Optional<TransactionType> findById(@PathVariable Long id) throws NoContentException {
        return service.findTransactionTypeByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TransactionType> findAll() {
        return service.findAll();
    }
}
