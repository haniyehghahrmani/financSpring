package org.java.financespring.service;

import org.java.financespring.model.Bonus;

import java.util.List;

public interface BonusService {

    Bonus save(Bonus bonus);

    Bonus edit(Long id, Bonus bonus);

    void remove(Long id);

    List<Bonus> findAll();

    Bonus findById(Long id);
}