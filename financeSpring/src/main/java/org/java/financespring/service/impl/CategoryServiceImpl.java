package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Category;
import org.java.financespring.repository.CategoryRepository;
import org.java.financespring.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository repository;
    
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category edit(Long id, Category category) throws NoContentException {
        Category existingCategory = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Category Was Found with id " + id + " To Update!")
                );
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setBudget(category.getBudget());
        existingCategory.setTransaction(category.getTransaction());
        existingCategory.setCategory(category.getCategory());

        return repository.saveAndFlush(existingCategory);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findCategoryByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Category Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Category> findCategoryByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Category> optional = repository.findCategoryByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Category Was Found with id : " + id);
        }
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No category found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
