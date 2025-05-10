package org.java.financespring.repository;

import org.java.financespring.model.FinancialDocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentStatusRepository extends JpaRepository<FinancialDocumentStatus, Long> {

    @Modifying
    @Query("update FinancialDocumentStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
