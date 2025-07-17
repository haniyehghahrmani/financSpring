package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.CheckPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckPaymentRepository extends JpaRepository<CheckPayment, Long> {

    @Modifying
    @Query("update CheckPaymentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<CheckPayment> findCheckPaymentByIdAndDeletedFalse(Long id);

}
