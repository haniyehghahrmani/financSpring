package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Transaction;
import org.java.financespring.service.TransactionService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public String transactionForm(Model model) {
        model.addAttribute("transactionList", service.findAll());
        model.addAttribute("transaction", new Transaction());
        return "transaction";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Transaction save(@Valid Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(transaction);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Transaction update(@PathVariable Long id, @Valid Transaction transaction, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(id, transaction);
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
    public Optional<Transaction> findById(@PathVariable Long id) throws NoContentException {
        return service.findTransactionByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Transaction> findAll() {
        return service.findAll();
    }
}
