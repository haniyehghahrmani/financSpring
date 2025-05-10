package org.java.financespring.repository;

import org.java.financespring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update UserEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<User> findUserByIdAndDeletedFalse(Long id);
}
