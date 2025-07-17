package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {

    @Modifying
    @Query("update EmploymentTypeEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<EmploymentType> findEmploymentTypeByIdAndDeletedFalse(Long id);
}
