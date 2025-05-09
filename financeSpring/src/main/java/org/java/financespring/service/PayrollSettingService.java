package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.PayrollSetting;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PayrollSettingService {

    PayrollSetting save(PayrollSetting payrollSetting);

    PayrollSetting edit(Long id, PayrollSetting payrollSetting);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<PayrollSetting> findAll();

    PayrollSetting findById(Long id);
}
