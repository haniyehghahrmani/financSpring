package org.java.financespring.service;

import org.java.financespring.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category edit(Long id, Category category);

    void remove(Long id);

    List<Category> findAll();

    Category findById(Long id);
}