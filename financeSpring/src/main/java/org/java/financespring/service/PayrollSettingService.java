package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.PayrollSetting;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PayrollSettingService {

    PayrollSetting save(PayrollSetting payrollSetting);

    PayrollSetting edit(Long id, PayrollSetting payrollSetting) throws NoContentException;

    void remove(Long id);

    @Transactional
    PayrollSetting logicalRemove(Long id) throws NoContentException;

    Optional<PayrollSetting> findPayrollSettingByIdAndDeletedFalse(Long id) throws NoContentException;

    List<PayrollSetting> findAll();

    PayrollSetting findById(Long id);
}
