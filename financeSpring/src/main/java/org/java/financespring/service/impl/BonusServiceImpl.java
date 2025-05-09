package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Bonus;
import org.java.financespring.repository.BonusRepository;
import org.java.financespring.service.BonusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusServiceImpl implements BonusService {

    private final BonusRepository repository;

    public BonusServiceImpl(BonusRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bonus save(Bonus bonus) {
        return repository.save(bonus);
    }

    @Override
    public Bonus edit(Long id, Bonus bonus) throws NoContentException {
        Bonus existingBonus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Bonus Type Was Found with id " + id + " To Update!")
                );
        existingBonus.setEmployee(bonus.getEmployee());
        existingBonus.setPayroll(bonus.getPayroll());
        existingBonus.setAmount(bonus.getAmount());
        existingBonus.setReason(bonus.getReason());
        existingBonus.setGrantedDate(bonus.getGrantedDate());
        existingBonus.setTaxable(bonus.isTaxable());

        return repository.saveAndFlush(existingBonus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Bonus> findAll() {
        return repository.findAll();
    }

    @Override
    public Bonus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No bonus found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
