package org.java.financespring.repository;

import org.java.financespring.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    @Modifying
    @Query("update TransactionTypeEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
