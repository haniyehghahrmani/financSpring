package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Payment;
import org.java.financespring.repository.PaymentRepository;
import org.java.financespring.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Payment edit(Long id, Payment payment) throws NoContentException {
        Payment existingPayment = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Payment Found with id " + id + " To Update!")
                );

        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setDescription(payment.getDescription());
        existingPayment.setIsActive(payment.getIsActive());

        return repository.saveAndFlush(existingPayment);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Payment findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("Payment Not Found"));
    }
}
