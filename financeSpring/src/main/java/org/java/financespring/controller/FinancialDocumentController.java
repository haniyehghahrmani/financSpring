package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.FinancialDocument;
import org.java.financespring.service.FinancialDocumentService;
import org.java.financespring.service.FinancialDocumentStatusService;
import org.java.financespring.service.FinancialDocumentTypeService;
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
@RequestMapping("/financialDocument")
public class FinancialDocumentController {

    private final FinancialDocumentService service;

    private final FinancialDocumentTypeService typeService;

    protected final FinancialDocumentStatusService statusService;

    public FinancialDocumentController(FinancialDocumentService service, FinancialDocumentTypeService typeService, FinancialDocumentStatusService statusService) {
        this.service = service;
        this.typeService = typeService;
        this.statusService = statusService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST)
    public FinancialDocument save(@Valid FinancialDocument financialDocument, Model model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        return service.save(financialDocument);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FinancialDocument edit(@Valid FinancialDocument financialDocument, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(financialDocument.getId(),financialDocument);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public FinancialDocument remove(@PathVariable Long id) throws NoContentException {
        return service.logicalRemove(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<FinancialDocument> findById(@PathVariable Long id) throws NoContentException {
        return service.findFinancialDocumentByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<FinancialDocument> findAll(Model model) {
        return service.findAll();
    }
}
