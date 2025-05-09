package org.java.financespring.repository;

import org.java.financespring.model.PayrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollStatusRepository extends JpaRepository<PayrollStatus, Long> {
}
