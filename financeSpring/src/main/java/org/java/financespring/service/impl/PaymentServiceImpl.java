package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Payment;
import org.java.financespring.repository.pgrepo.PaymentRepository;
import org.java.financespring.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findPaymentByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Payment Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Payment findById(Long id) throws NoContentException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No Payment found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }    }
}
