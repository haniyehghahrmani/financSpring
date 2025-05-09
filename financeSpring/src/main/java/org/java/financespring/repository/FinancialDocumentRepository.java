package org.java.financespring.repository;

import org.java.financespring.model.FinancialDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentRepository extends JpaRepository<FinancialDocument, Long> {
}
