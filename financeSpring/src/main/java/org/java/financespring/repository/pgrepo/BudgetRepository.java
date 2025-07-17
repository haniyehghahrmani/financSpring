package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Modifying
    @Query("update BudgetEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Budget> findBudgetByIdAndDeletedFalse(Long id);
}
