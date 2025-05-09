package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.InvoiceItem;
import org.java.financespring.repository.InvoiceItemRepository;
import org.java.financespring.service.InvoiceItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository repository;

    public InvoiceItemServiceImpl(InvoiceItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvoiceItem save(InvoiceItem invoiceItem) {
        return repository.save(invoiceItem);
    }

    @Override
    public InvoiceItem edit(Long id, InvoiceItem invoiceItem) throws NoContentException {
        InvoiceItem existingInvoiceItem = (InvoiceItem) repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active InvoiceItem Was Found with id " + id + " To Update!")
                );

        existingInvoiceItem.setDescription(invoiceItem.getDescription());
        existingInvoiceItem.setUnitPrice(invoiceItem.getUnitPrice());
        existingInvoiceItem.setQuantity(invoiceItem.getQuantity());
        existingInvoiceItem.setTotalPrice(invoiceItem.getTotalPrice());
        existingInvoiceItem.setDiscount(invoiceItem.getDiscount());
        existingInvoiceItem.setFinalPrice(invoiceItem.getFinalPrice());
        existingInvoiceItem.setIsActive(invoiceItem.getIsActive());

        return repository.saveAndFlush(existingInvoiceItem);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<InvoiceItem> findAll() {
        return repository.findAll();
    }

    @Override
    public InvoiceItem findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No Invoice Item found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }    }
}
