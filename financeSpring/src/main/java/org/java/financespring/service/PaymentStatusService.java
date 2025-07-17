package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.PaymentStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentStatusService {

    PaymentStatus save(PaymentStatus paymentStatus);

    PaymentStatus edit(Long id, PaymentStatus paymentStatus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<PaymentStatus> findAll();

    PaymentStatus findById(Long id) throws NoContentException;
}