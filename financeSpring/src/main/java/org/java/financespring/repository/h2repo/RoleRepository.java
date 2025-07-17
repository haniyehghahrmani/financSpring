package org.java.financespring.repository.h2repo;

import org.java.financespring.model.h2model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query("update RoleEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Role> findRoleByIdAndDeletedFalse(Long id);
}
