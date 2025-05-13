package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Payroll;
import org.java.financespring.repository.PayrollRepository;
import org.java.financespring.service.PayrollService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollServiceImpl implements PayrollService {
    
    private final PayrollRepository repository;

    public PayrollServiceImpl(PayrollRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payroll save(Payroll payroll) {
        return repository.save(payroll);
    }

    @Override
    public Payroll edit(Long id, Payroll payroll) throws NoContentException {
        Payroll existingPayroll = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Payroll Was Found with id " + id + " To Update!")
                );
        existingPayroll.setEmployee(payroll.getEmployee());
        existingPayroll.setPayPeriodStart(payroll.getPayPeriodStart());
        existingPayroll.setPayPeriodEnd(payroll.getPayPeriodEnd());
        existingPayroll.setBaseSalary(payroll.getBaseSalary());
        existingPayroll.setAllowances(payroll.getAllowances());
        existingPayroll.setOvertimePay(payroll.getOvertimePay());
        existingPayroll.setDelayPenalty(payroll.getDelayPenalty());
        existingPayroll.setInsuranceWithheld(payroll.getInsuranceWithheld());
        existingPayroll.setNetSalary(payroll.getNetSalary());

        return repository.saveAndFlush(existingPayroll);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Payroll logicalRemove(Long id) throws NoContentException {
        repository.findPayrollByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Payroll Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<Payroll> findPayrollByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Payroll> optional = repository.findPayrollByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Payroll Was Found with id : " + id);
        }
    }

    @Override
    public List<Payroll> findAll() {
        return repository.findAll();
    }

    @Override
    public Payroll findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No payroll found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
