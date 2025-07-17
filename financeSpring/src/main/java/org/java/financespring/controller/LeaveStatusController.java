package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.LeaveStatus;
import org.java.financespring.service.LeaveStatusService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/leaveStatuses")
public class LeaveStatusController {

    private final LeaveStatusService service;

    public LeaveStatusController(LeaveStatusService service) {
        this.service = service;
    }

    @GetMapping
    public String leaveStatusForm(Model model) {
        model.addAttribute("leaveStatusList", service.findAll());
        model.addAttribute("leaveStatus", new LeaveStatus());
        return "leave-status"; // صفحه‌ای که لیست وضعیت‌های مرخصی و فرم جدید را نمایش می‌دهد
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public LeaveStatus save(@Valid LeaveStatus leaveStatus, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(leaveStatus);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LeaveStatus update(@Valid LeaveStatus leaveStatus, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(leaveStatus.getId(), leaveStatus);
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
    public Optional<LeaveStatus> findById(@PathVariable Long id) throws NoContentException {
        return service.findLeaveStatusByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<LeaveStatus> findAll() {
        return service.findAll();
    }
}
