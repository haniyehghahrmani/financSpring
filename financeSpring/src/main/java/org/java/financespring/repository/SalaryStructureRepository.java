package org.java.financespring.repository;

import org.java.financespring.model.SalaryStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure, Long> {

    @Modifying
    @Query("update SalaryStructureEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
