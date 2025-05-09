package org.java.financespring.repository;

import org.java.financespring.model.Bank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b FROM Bank b WHERE b.id = :id AND b.active = true")
    Optional<Bank> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM Bank b WHERE b.active = true")
    List<Bank> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE Bank b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
