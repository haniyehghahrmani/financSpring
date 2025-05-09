package org.java.financespring.repository;

import org.java.financespring.model.FinancialDocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentStatusRepository extends JpaRepository<FinancialDocumentStatus, Long> {
}
