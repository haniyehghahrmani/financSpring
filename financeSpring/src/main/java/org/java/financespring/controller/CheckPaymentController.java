package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.CheckPayment;
import org.java.financespring.service.CheckPaymentService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/check-payments")
public class CheckPaymentController {

    private final CheckPaymentService service;

    public CheckPaymentController(CheckPaymentService service) {
        this.service = service;
    }

    @GetMapping
    public String checkPaymentForm(Model model) {
        model.addAttribute("checkPaymentList", service.findAll());
        model.addAttribute("checkPayment", new CheckPayment());
        return "check-payment";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CheckPayment save(@Valid CheckPayment checkPayment, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(checkPayment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CheckPayment update(@PathVariable Long id, @Valid CheckPayment checkPayment, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(id, checkPayment);
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
    public CheckPayment findById(@PathVariable Long id) throws NoContentException {
        return service.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CheckPayment> findAll() {
        return service.findAll();
    }
}
