package org.java.financespring.repository;

import org.java.financespring.model.Bank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Modifying
    @Query("update BankEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Bank> findBankByIdAndDeletedFalse(Long id);
}
