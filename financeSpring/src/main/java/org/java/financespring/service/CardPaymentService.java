package org.java.financespring.service;

import org.java.financespring.model.CardPayment;

import java.util.List;

public interface CardPaymentService {

    CardPayment save(CardPayment cardPayment);

    CardPayment edit(Long id, CardPayment cardPayment);

    void remove(Long id);

    List<CardPayment> findAll();

    CardPayment findById(Long id);
}