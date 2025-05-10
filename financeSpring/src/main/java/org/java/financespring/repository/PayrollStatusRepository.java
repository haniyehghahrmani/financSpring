package org.java.financespring.repository;

import org.java.financespring.model.PayrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollStatusRepository extends JpaRepository<PayrollStatus, Long> {

    @Modifying
    @Query("update PayrollStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
