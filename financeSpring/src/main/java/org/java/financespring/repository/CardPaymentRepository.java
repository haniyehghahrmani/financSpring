package org.java.financespring.repository;

import org.java.financespring.model.CardPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Long> {


    @Modifying
    @Query("update CardPaymentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<CardPayment> findCardPaymentByIdAndDeletedFalse(Long id);
}
