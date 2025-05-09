package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CashPayment;
import org.java.financespring.repository.CashPaymentRepository;
import org.java.financespring.service.CashPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {

    private final CashPaymentRepository repository;

    public CashPaymentServiceImpl(CashPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CashPayment save(CashPayment cashPayment) {
        return repository.save(cashPayment);
    }

    @Override
    public CashPayment edit(Long id, CashPayment cashPayment) throws NoContentException {
        CashPayment existingCashPayment = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Cash Payment Found with id " + id + " To Update!")
                );

        existingCashPayment.setReceivedBy(cashPayment.getReceivedBy());
        existingCashPayment.setLocation(cashPayment.getLocation());
        existingCashPayment.setIsActive(cashPayment.getIsActive());

        return repository.saveAndFlush(existingCashPayment);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CashPayment> findAll() {
        return repository.findAll();
    }

    @Override
    public CashPayment findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("No Cash Payment found with id " + id));
    }
}
