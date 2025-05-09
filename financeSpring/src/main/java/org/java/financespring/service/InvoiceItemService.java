package org.java.financespring.service;


import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.InvoiceItem;
import org.java.financespring.repository.InvoiceItemRepository;

import java.util.List;

public interface InvoiceItemService {

    InvoiceItem save(InvoiceItem invoiceItem);

    InvoiceItem edit(Long id, InvoiceItem invoiceItem) throws NoContentException;

    void remove(Long id);

    List<InvoiceItemRepository> findAll();

    InvoiceItemRepository findById(Long id) throws NoContentException;
}