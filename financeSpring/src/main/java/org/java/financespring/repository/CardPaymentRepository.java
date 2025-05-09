package org.java.financespring.repository;

import org.java.financespring.model.CardPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Long> {

    @Query("SELECT b FROM CardPayment b WHERE b.id = :id AND b.active = true")
    Optional<CardPayment> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM CardPayment b WHERE b.active = true")
    List<CardPayment> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE CardPayment b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
