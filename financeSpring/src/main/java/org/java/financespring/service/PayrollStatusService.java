package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.PayrollStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PayrollStatusService {

    PayrollStatus save(PayrollStatus payrollStatus);

    PayrollStatus edit(Long id, PayrollStatus payrollStatus);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<PayrollStatus> findPayrollStatusByIdAndDeletedFalse(Long id) throws NoContentException;

    List<PayrollStatus> findAll();

    PayrollStatus findById(Long id);
}
