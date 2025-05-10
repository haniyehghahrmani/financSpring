package org.java.financespring.repository;

import org.java.financespring.model.PayrollSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollSettingRepository extends JpaRepository<PayrollSetting, Long> {

    @Modifying
    @Query("update PayrollSettingEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<PayrollSetting> findPayrollSettingByIdAndDeletedFalse(Long id);
}
