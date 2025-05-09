package org.java.financespring.repository;

import org.java.financespring.model.CashPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {

    @Query("SELECT b FROM CashPayment b WHERE b.id = :id AND b.active = true")
    Optional<CashPayment> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM CashPayment b WHERE b.active = true")
    List<CashPayment> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE CashPayment b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
