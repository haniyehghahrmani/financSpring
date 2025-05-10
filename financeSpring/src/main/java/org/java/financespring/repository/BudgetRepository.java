package org.java.financespring.repository;

import org.java.financespring.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Modifying
    @Query("update BudgetEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
