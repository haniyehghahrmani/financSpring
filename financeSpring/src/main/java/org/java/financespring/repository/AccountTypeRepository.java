package org.java.financespring.repository;

import org.java.financespring.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Modifying
    @Query("update AccountTypeEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
