package org.java.financespring.repository.pgrepo;

import org.java.financespring.model.pgmodel.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update ProductEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    Optional<Product> findProductByIdAndDeletedFalse(Long id);

}
