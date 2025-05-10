package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CardPayment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardPaymentService {

    CardPayment save(CardPayment cardPayment);

    CardPayment edit(Long id, CardPayment cardPayment) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<CardPayment> findAll();

    CardPayment findById(Long id) throws NoContentException;
}