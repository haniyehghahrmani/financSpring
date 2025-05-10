package org.java.financespring.repository;

import org.java.financespring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("update PersonEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}
