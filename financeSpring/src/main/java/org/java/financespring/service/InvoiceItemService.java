package org.java.financespring.service;


import org.java.financespring.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemService {

    InvoiceItem save(InvoiceItem invoiceItem);

    InvoiceItem edit(Long id, InvoiceItem invoiceItem);

    void remove(Long id);

    List<InvoiceItem> findAll();

    InvoiceItem findById(Long id);
}