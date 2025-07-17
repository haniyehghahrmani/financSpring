package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.BudgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetStatusRepository extends JpaRepository<BudgetStatus,Long> {

    @Modifying
    @Query("update BudgetStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<BudgetStatus> findBudgetStatusByIdAndDeletedFalse(Long id);
}
