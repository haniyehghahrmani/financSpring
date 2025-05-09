package org.java.financespring.repository;

import org.java.financespring.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {

    @Query("SELECT b FROM PaymentStatus b WHERE b.id = :id AND b.active = true")
    Optional<PaymentStatus> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM PaymentStatus b WHERE b.active = true")
    List<PaymentStatus> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE PaymentStatus b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
