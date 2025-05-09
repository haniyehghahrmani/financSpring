package org.java.financespring.service;

import org.java.financespring.model.PayrollSetting;

import java.util.List;

public interface PayrollSettingService {

    PayrollSetting save(PayrollSetting payrollSetting);

    PayrollSetting edit(Long id, PayrollSetting payrollSetting);

    void remove(Long id);

    List<PayrollSetting> findAll();

    PayrollSetting findById(Long id);
}
