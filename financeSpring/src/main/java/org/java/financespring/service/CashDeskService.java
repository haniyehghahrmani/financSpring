package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.CashDesk;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CashDeskService {

    CashDesk save(CashDesk cashDesk);

    CashDesk edit(Long id, CashDesk cashDesk) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<CashDesk> findAll();

    CashDesk findById(Long id) throws NoContentException;
}