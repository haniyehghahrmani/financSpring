package org.java.financespring.repository;

import org.java.financespring.model.Payment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Query("update PaymentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Payment> findPaymentByIdAndDeletedFalse(Long id);

}
