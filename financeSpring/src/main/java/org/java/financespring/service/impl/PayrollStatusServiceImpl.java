package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.PayrollStatus;
import org.java.financespring.repository.pgrepo.PayrollStatusRepository;
import org.java.financespring.service.PayrollStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollStatusServiceImpl implements PayrollStatusService {

    private final PayrollStatusRepository repository;

    public PayrollStatusServiceImpl(PayrollStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public PayrollStatus save(PayrollStatus payrollStatus) {
        return repository.save(payrollStatus);
    }

    @Override
    public PayrollStatus edit(Long id, PayrollStatus payrollStatus) throws NoContentException {
        PayrollStatus existingPayrollStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active PayrollStatus Was Found with id " + id + " To Update!")
                );
        existingPayrollStatus.setName(payrollStatus.getName());

        return repository.saveAndFlush(existingPayrollStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public PayrollStatus logicalRemove(Long id) throws NoContentException {
        repository.findPayrollStatusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active PayrollStatus Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<PayrollStatus> findPayrollStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<PayrollStatus> optional = repository.findPayrollStatusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active PayrollStatus Was Found with id : " + id);
        }
    }

    @Override
    public List<PayrollStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public PayrollStatus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No payrollStatus found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
