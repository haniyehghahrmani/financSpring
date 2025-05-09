package org.java.financespring.service;

import org.java.financespring.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment save(Payment payment);

    Payment edit(Long id, Payment payment);

    void remove(Long id);

    List<Payment> findAll();

    Payment findById(Long id);
}