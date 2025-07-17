package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Invoice;
import org.java.financespring.repository.pgrepo.InvoiceRepository;
import org.java.financespring.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;

    public InvoiceServiceImpl(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    @Override
    public Invoice edit(Long id, Invoice invoice) throws NoContentException {
        Invoice existingInvoice = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Invoice Was Found with id " + id + " To Update!")
                );

        existingInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
        existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
        existingInvoice.setDueDate(invoice.getDueDate());
        existingInvoice.setTotalAmount(invoice.getTotalAmount());
        existingInvoice.setPaidAmount(invoice.getPaidAmount());
        existingInvoice.setRemainingAmount(invoice.getRemainingAmount());
        existingInvoice.setInvoiceStatus(invoice.getInvoiceStatus());
        existingInvoice.setCustomerName(invoice.getCustomerName());
        existingInvoice.setCustomerAddress(invoice.getCustomerAddress());
        existingInvoice.setCustomerPhone(invoice.getCustomerPhone());
        existingInvoice.setDescription(invoice.getDescription());
        existingInvoice.setAccountID(invoice.getAccountID());
        existingInvoice.setUserID(invoice.getUserID());
        existingInvoice.setInvoiceItems(invoice.getInvoiceItems());
//        existingInvoice.setTransactions(invoice.getTransactions());
        existingInvoice.setIsActive(invoice.getIsActive());

        return repository.saveAndFlush(existingInvoice);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findInvoiceByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Invoice Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Invoice> findAll() {
        return repository.findAll();
    }

    @Override
    public Invoice findById(Long id) throws NoContentException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No Invoice found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }    }
}
