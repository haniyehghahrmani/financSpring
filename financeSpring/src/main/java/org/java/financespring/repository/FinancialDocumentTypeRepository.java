package org.java.financespring.repository;

import org.java.financespring.model.FinancialDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDocumentTypeRepository extends JpaRepository<FinancialDocumentType, Long> {
}
