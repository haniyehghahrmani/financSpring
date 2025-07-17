package org.java.financespring.service.impl;

import jakarta.transaction.Transactional;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Bonus;
import org.java.financespring.repository.pgrepo.BonusRepository;
import org.java.financespring.service.BonusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public Bonus logicalRemove(Long id) throws NoContentException {
        repository.findBonusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Bonus Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<Bonus> findBonusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Bonus> optional = repository.findBonusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Bonus Was Found with id : " + id);
        }
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
