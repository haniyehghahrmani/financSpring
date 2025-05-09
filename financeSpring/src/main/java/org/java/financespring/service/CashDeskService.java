package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CashDesk;

import java.util.List;

public interface CashDeskService {

    CashDesk save(CashDesk cashDesk);

    CashDesk edit(Long id, CashDesk cashDesk) throws NoContentException;

    void remove(Long id);

    List<CashDesk> findAll();

    CashDesk findById(Long id) throws NoContentException;
}