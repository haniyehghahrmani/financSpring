package org.java.financespring.service;

import org.java.financespring.model.PaymentStatus;

import java.util.List;

public interface PaymentStatusService {

    PaymentStatus save(PaymentStatus paymentStatus);

    PaymentStatus edit(Long id, PaymentStatus paymentStatus);

    void remove(Long id);

    List<PaymentStatus> findAll();

    PaymentStatus findById(Long id);
}