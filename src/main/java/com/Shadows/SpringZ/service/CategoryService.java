package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Category;

import java.util.List;

/**
 * Service Layer for Category CRUD.
 * Keeps controllers thin and repository access centralized.
 */
public interface CategoryService {
    Category createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryByID(Long id);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
}
