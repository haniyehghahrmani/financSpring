package org.java.financespring.service;

import org.java.financespring.model.CashPayment;

import java.util.List;

public interface CashPaymentService {

    CashPayment save(CashPayment cashPayment);

    CashPayment edit(Long id, CashPayment cashPayment);

    void remove(Long id);

    List<CashPayment> findAll();

    CashPayment findById(Long id);
}