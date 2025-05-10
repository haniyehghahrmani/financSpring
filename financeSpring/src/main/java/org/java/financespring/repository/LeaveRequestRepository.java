package org.java.financespring.repository;

import org.java.financespring.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Modifying
    @Query("update LeaveRequestEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<LeaveRequest> findLeaveRequestByIdAndDeletedFalse(Long id);
}
