package org.java.financespring.service;

import org.java.financespring.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice save(Invoice invoice);

    Invoice edit(Long id, Invoice invoice);

    void remove(Long id);

    List<Invoice> findAll();

    Invoice findById(Long id);
}