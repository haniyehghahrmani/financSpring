package org.java.financespring.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.LeaveType;
import org.java.financespring.service.LeaveTypeService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/leaveTypes")
public class LeaveTypeController {

    private final LeaveTypeService service;

    public LeaveTypeController(LeaveTypeService service) {
        this.service = service;
    }

    @GetMapping
    public String leaveTypeForm(Model model) {
        model.addAttribute("leaveTypeList", service.findAll());
        model.addAttribute("leaveType", new LeaveType());
        return "leave-type"; // صفحه‌ی مورد نظر برای نمایش فهرست و فرم
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public LeaveType save(@Valid LeaveType leaveType, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.save(leaveType);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LeaveType update(@Valid LeaveType leaveType, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList().toString()
            );
        }
        return service.edit(leaveType.getId(),leaveType);
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
    public Optional<LeaveType> findById(@PathVariable Long id) throws NoContentException {
        return service.findLeaveTypeByIdAndDeletedFalse(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<LeaveType> findAll() {
        return service.findAll();
    }
}
