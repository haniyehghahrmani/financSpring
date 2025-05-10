package org.java.financespring.repository;

import org.java.financespring.model.CashDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashDeskRepository extends JpaRepository<CashDesk, Long> {

    @Modifying
    @Query("update CashDeskEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<CashDesk> findCashDeskByIdAndDeletedFalse(Long id);
}
