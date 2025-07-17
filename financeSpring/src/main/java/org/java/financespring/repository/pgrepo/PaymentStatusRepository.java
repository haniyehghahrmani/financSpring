package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {

    @Modifying
    @Query("update PaymentStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<PaymentStatus> findPaymentStatusByIdAndDeletedFalse(Long id);

}
