package org.java.financespring.service;


import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.InvoiceItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvoiceItemService {

    InvoiceItem save(InvoiceItem invoiceItem);

    InvoiceItem edit(Long id, InvoiceItem invoiceItem) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<InvoiceItem> findAll();

    InvoiceItem findById(Long id) throws NoContentException;
}