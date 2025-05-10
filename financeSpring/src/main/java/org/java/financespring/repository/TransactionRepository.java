package org.java.financespring.repository;

import org.java.financespring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Modifying
    @Query("update TransactionEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Transaction> findTransactionByIdAndDeletedFalse(Long id);
}
