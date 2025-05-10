package org.java.financespring.repository;

import org.java.financespring.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Modifying
    @Query("update PayrollEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
