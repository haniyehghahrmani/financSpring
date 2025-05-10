package org.java.financespring.repository;

import org.java.financespring.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Modifying
    @Query("update InvoiceEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Invoice> findInvoiceByIdAndDeletedFalse(Long id);

}
