package org.java.financespring.repository;

import org.java.financespring.model.FinancialDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentTypeRepository extends JpaRepository<FinancialDocumentType, Long> {

    @Modifying
    @Query("update FinancialDocumentTypeEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
