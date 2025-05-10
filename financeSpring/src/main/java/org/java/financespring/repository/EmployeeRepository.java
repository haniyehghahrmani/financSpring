package org.java.financespring.repository;

import org.java.financespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("update EmployeeEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
