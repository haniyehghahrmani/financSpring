package org.java.financespring.repository;

import org.java.financespring.model.Payment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT b FROM Payment b WHERE b.id = :id AND b.active = true")
    Optional<Payment> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM Payment b WHERE b.active = true")
    List<Payment> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE Payment b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
