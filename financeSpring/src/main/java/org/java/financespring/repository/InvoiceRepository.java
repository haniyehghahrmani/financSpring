package org.java.financespring.repository;

import org.java.financespring.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT b FROM Invoice b WHERE b.id = :id AND b.active = true")
    Optional<Invoice> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM Invoice b WHERE b.active = true")
    List<Invoice> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE Invoice b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
