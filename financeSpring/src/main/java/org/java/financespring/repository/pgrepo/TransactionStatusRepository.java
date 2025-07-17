package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {

    @Modifying
    @Query("update TransactionStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<TransactionStatus> findTransactionStatusByIdAndDeletedFalse(Long id);
}
