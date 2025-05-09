package org.java.financespring.repository;

import org.java.financespring.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Long> {
}
