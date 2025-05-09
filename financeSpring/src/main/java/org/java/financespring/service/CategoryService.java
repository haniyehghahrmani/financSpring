package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category edit(Long id, Category category);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Category> findAll();

    Category findById(Long id);
}