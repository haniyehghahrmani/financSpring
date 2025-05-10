package org.java.financespring.repository;

import org.java.financespring.model.Bonus;
import org.java.financespring.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    @Modifying
    @Query("update BonusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Bonus> findBonusByIdAndDeletedFalse(Long id);

}
