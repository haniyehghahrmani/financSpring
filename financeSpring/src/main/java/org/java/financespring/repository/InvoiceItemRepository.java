package org.java.financespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemRepository, Long> {

    @Query("SELECT b FROM InvoiceItem b WHERE b.id = :id AND b.active = true")
    Optional<InvoiceItemRepository> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM InvoiceItem b WHERE b.active = true")
    List<InvoiceItemRepository> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE InvoiceItem b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
