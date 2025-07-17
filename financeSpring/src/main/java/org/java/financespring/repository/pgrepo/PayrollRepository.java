package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Modifying
    @Query("update PayrollEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Payroll> findPayrollByIdAndDeletedFalse(Long id);
}
