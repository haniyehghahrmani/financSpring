package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.FinancialDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialDocumentRepository extends JpaRepository<FinancialDocument, Long> {

    @Modifying
    @Query("update FinancialDocumentEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<FinancialDocument> findFinancialDocumentByIdAndDeletedFalse(Long id);
}
