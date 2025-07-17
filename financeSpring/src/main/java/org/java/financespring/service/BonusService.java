package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Bonus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BonusService {

    Bonus save(Bonus bonus);

    Bonus edit(Long id, Bonus bonus) throws NoContentException;

    void remove(Long id);

    @Transactional
    Bonus logicalRemove(Long id) throws NoContentException;

    Optional<Bonus> findBonusByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Bonus> findAll();

    Bonus findById(Long id);
}