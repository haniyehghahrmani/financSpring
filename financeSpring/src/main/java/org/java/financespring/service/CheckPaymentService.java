package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.CheckPayment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CheckPaymentService {

    CheckPayment save(CheckPayment checkPayment);

    CheckPayment edit(Long id, CheckPayment checkPayment) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<CheckPayment> findAll();

    CheckPayment findById(Long id) throws NoContentException;
}