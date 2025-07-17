package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    @Modifying
    @Query("update DeductionEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Deduction> findDeductionByIdAndDeletedFalse(Long id);
}
