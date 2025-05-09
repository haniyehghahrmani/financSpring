package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CheckPayment;
import org.java.financespring.repository.CheckPaymentRepository;
import org.java.financespring.service.CheckPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPaymentServiceImpl implements CheckPaymentService {

    private final CheckPaymentRepository repository;

    public CheckPaymentServiceImpl(CheckPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CheckPayment save(CheckPayment checkPayment) {
        return repository.save(checkPayment);
    }

    @Override
    public CheckPayment edit(Long id, CheckPayment checkPayment) throws NoContentException {
        CheckPayment existingCheckPayment = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No CheckPayment Found with id " + id + " To Update!")
                );
        existingCheckPayment.setChequeNumber(checkPayment.getChequeNumber());
        existingCheckPayment.setIssueDate(checkPayment.getIssueDate());
        existingCheckPayment.setAccount(checkPayment.getAccount());
        existingCheckPayment.setIsActive(checkPayment.getIsActive());

        return repository.saveAndFlush(existingCheckPayment);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CheckPayment> findAll() {
        return repository.findAll();
    }

    @Override
    public CheckPayment findById(Long id) throws NoContentException {
        return repository.findById(id).orElseThrow(() -> new NoContentException("Check Payment Not Found"));
    }
}
