package org.java.financespring.repository;

import org.java.financespring.model.BudgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetStatusRepository extends JpaRepository<BudgetStatus,Long> {

    @Modifying
    @Query("update BudgetStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
