package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.PaymentStatus;
import org.java.financespring.repository.PaymentStatusRepository;
import org.java.financespring.service.PaymentStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository repository;

    public PaymentStatusServiceImpl(PaymentStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentStatus save(PaymentStatus paymentStatus) {
        return repository.save(paymentStatus);
    }

    @Override
    public PaymentStatus edit(Long id, PaymentStatus paymentStatus) throws NoContentException {
        PaymentStatus existingPaymentStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active PaymentStatus Found with id " + id + " To Update!")
                );

        existingPaymentStatus.setStatusName(paymentStatus.getStatusName());
        existingPaymentStatus.setDescription(paymentStatus.getDescription());
        existingPaymentStatus.setIsActive(paymentStatus.getIsActive());

        return repository.saveAndFlush(existingPaymentStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PaymentStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public PaymentStatus findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("PaymentStatus Not Found"));
    }
}
