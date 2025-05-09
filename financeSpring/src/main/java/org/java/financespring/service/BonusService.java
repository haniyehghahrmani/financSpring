package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Bonus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BonusService {

    Bonus save(Bonus bonus);

    Bonus edit(Long id, Bonus bonus) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Bonus> findAll();

    Bonus findById(Long id);
}