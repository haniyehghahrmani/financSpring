package org.java.financespring.service;

import org.java.financespring.model.PayrollStatus;

import java.util.List;

public interface PayrollStatusService {

    PayrollStatus save(PayrollStatus payrollStatus);

    PayrollStatus edit(Long id, PayrollStatus payrollStatus);

    void remove(Long id);

    List<PayrollStatus> findAll();

    PayrollStatus findById(Long id);
}
