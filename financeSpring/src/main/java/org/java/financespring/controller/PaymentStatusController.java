package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.PaymentStatus;
import org.java.financespring.service.PaymentStatusService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paymentStatuses")
public class PaymentStatusController {

    private final PaymentStatusService service;

    public PaymentStatusController(PaymentStatusService service) {
        this.service = service;
    }

    @GetMapping
    public String paymentStatusForm(Model model) {
        model.addAttribute("paymentStatusList", service.findAll());
        model.addAttribute("paymentStatus", new PaymentStatus());
        return "paymentStatus";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PaymentStatus save(@Valid PaymentStatus paymentStatus, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(paymentStatus);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PaymentStatus update(@PathVariable Long id, @Valid PaymentStatus paymentStatus, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(id, paymentStatus);
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
    public PaymentStatus findById(@PathVariable Long id) throws NoContentException {
        return service.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PaymentStatus> findAll() {
        return service.findAll();
    }
}
