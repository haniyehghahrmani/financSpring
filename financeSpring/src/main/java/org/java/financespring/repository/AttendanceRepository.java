package org.java.financespring.repository;

import org.java.financespring.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Modifying
    @Query("update AttendanceEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
