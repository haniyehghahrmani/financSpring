package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.PayrollSetting;
import org.java.financespring.repository.pgrepo.PayrollSettingRepository;
import org.java.financespring.service.PayrollSettingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollSettingServiceImpl implements PayrollSettingService {

    private final PayrollSettingRepository repository;

    public PayrollSettingServiceImpl(PayrollSettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public PayrollSetting save(PayrollSetting payrollSetting) {
        return repository.save(payrollSetting);
    }

    @Override
    public PayrollSetting edit(Long id, PayrollSetting payrollSetting) throws NoContentException {
        PayrollSetting existingPayrollSetting = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active PayrollSetting Was Found with id " + id + " To Update!")
                );
        existingPayrollSetting.setTaxRate(payrollSetting.getTaxRate());
        existingPayrollSetting.setOvertimeRate(payrollSetting.getOvertimeRate());
        existingPayrollSetting.setInsuranceRate(payrollSetting.getInsuranceRate());
        existingPayrollSetting.setStandardMonthlyHours(payrollSetting.getStandardMonthlyHours());
        existingPayrollSetting.setAutoCalculateDeductions(payrollSetting.isAutoCalculateDeductions());
        existingPayrollSetting.setCurrency(payrollSetting.getCurrency());
        existingPayrollSetting.setActive(payrollSetting.isActive());

        return repository.saveAndFlush(existingPayrollSetting);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public PayrollSetting logicalRemove(Long id) throws NoContentException {
        repository.findPayrollSettingByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active PayrollSetting Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<PayrollSetting> findPayrollSettingByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<PayrollSetting> optional = repository.findPayrollSettingByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active PayrollSetting Was Found with id : " + id);
        }
    }

    @Override
    public List<PayrollSetting> findAll() {
        return repository.findAll();
    }

    @Override
    public PayrollSetting findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No payrollSetting found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
