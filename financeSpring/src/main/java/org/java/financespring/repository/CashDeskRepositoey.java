package org.java.financespring.repository;

import org.java.financespring.model.CashDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashDeskRepositoey extends JpaRepository<CashDesk, Long> {

    @Query("SELECT b FROM CashDesk b WHERE b.id = :id AND b.active = true")
    Optional<CashDesk> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM CashDesk b WHERE b.active = true")
    List<CashDesk> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE CashDesk b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
