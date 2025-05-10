package org.java.financespring.repository;

import org.java.financespring.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {

    @Modifying
    @Query("update TransactionStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
