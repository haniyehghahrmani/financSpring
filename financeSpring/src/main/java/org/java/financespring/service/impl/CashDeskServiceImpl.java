package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CashDesk;
import org.java.financespring.repository.CashDeskRepository;
import org.java.financespring.service.CashDeskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashDeskServiceImpl implements CashDeskService {

    private final CashDeskRepository repository;

    public CashDeskServiceImpl(CashDeskRepository repository) {
        this.repository = repository;
    }

    @Override
    public CashDesk save(CashDesk cashDesk) {
        return repository.save(cashDesk);
    }

    @Override
    public CashDesk edit(Long id, CashDesk cashDesk) throws NoContentException {
        CashDesk existingCashDesk = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Cash Desk Found with id " + id + " To Update!")
                );

        existingCashDesk.setName(cashDesk.getName());
        existingCashDesk.setLocation(cashDesk.getLocation());
        existingCashDesk.setManagerName(cashDesk.getManagerName());
        existingCashDesk.setContactNumber(cashDesk.getContactNumber());
        existingCashDesk.setActive(cashDesk.getActive());

        return repository.saveAndFlush(existingCashDesk);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CashDesk> findAll() {
        return repository.findAll();
    }

    @Override
    public CashDesk findById(Long id) throws NoContentException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No Cash Desk found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
