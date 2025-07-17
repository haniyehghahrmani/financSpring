package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);

    Category edit(Long id, Category category) throws NoContentException;

    void remove(Long id);

    @Transactional
    Category logicalRemove(Long id) throws NoContentException;

    Optional<Category> findCategoryByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Category> findAll();

    Category findById(Long id);
}