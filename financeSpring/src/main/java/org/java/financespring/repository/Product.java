package org.java.financespring.repository;

import org.java.financespring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT b FROM Product b WHERE b.id = :id AND b.active = true")
    Optional<Product> findByIdAndActiveTrue(Long id);

    @Query("SELECT b FROM Product b WHERE b.active = true")
    List<Product> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("UPDATE Product b SET b.active = false WHERE b.id = :id")
    void logicallyDeleteById(Long id);
}
