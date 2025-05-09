package org.java.financespring.repository;

import org.java.financespring.model.PayrollSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollSettingRepository extends JpaRepository<PayrollSetting, Long> {
}
