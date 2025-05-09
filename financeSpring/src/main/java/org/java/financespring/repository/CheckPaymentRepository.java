package org.java.financespring.repository;

import org.java.financespring.model.CheckPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckPaymentRepository extends JpaRepository<CheckPayment, Long> {

    @Query("SELECT b FROM CheckPayment b WHERE b.id = :id AND b.active = true")
    Optional<CheckPayment> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM CheckPayment b WHERE b.active = true")
    List<CheckPayment> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE CheckPayment b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
