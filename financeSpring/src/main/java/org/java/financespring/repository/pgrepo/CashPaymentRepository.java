package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.CashPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {

    @Modifying
    @Query("update CashPaymentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<CashPayment> findCashPaymentByIdAndDeletedFalse(Long id);

}
