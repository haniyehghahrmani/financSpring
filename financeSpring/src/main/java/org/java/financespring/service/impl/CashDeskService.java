package org.java.financespring.service.impl;

import org.java.financespring.model.CashDesk;

import java.util.List;

public interface CashDeskService {

    CashDesk save(CashDesk cashDesk);

    CashDesk edit(Long id, CashDesk cashDesk);

    void remove(Long id);

    List<CashDesk> findAll();

    CashDesk findById(Long id);
}