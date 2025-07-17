package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Query("update CategoryEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Category> findCategoryByIdAndDeletedFalse(Long id);
}
