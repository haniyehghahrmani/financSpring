package org.java.financespring.repository.h2repo;

import org.java.financespring.model.h2model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Long> {

    @Modifying
    @Query("update LeaveStatusEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<LeaveStatus> findLeaveStatusByIdAndDeletedFalse(Long id);
}
