package org.java.financespring.repository;

import org.java.financespring.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("update AccountEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Account> findAccountByIdAndDeletedFalse(Long id);
}
