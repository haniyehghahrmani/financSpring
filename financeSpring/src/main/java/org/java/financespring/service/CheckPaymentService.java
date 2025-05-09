package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CheckPayment;

import java.util.List;

public interface CheckPaymentService {

    CheckPayment save(CheckPayment checkPayment);

    CheckPayment edit(Long id, CheckPayment checkPayment) throws NoContentException;

    void remove(Long id);

    List<CheckPayment> findAll();

    CheckPayment findById(Long id) throws NoContentException;
}