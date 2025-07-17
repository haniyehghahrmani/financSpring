package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

    @Modifying
    @Query("update InvoiceItemEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<InvoiceItem> findInvoiceItemByIdAndDeletedFalse(Long id);

}
