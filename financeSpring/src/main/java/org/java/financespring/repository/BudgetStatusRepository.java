package org.java.financespring.repository;

import org.java.financespring.model.BudgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetStatusRepository extends JpaRepository<BudgetStatus,Long> {
}
