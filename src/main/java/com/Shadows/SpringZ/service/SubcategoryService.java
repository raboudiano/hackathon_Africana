package com.Shadows.SpringZ.service;

import com.Shadows.SpringZ.model.Subcategory;

import java.util.List;

/**
 * Service Layer for Subcategory CRUD.
 */
public interface SubcategoryService {
    Subcategory createSubcategory(Subcategory subcategory);

    List<Subcategory> getAllSubcategories();

    Subcategory getSubcategoryByID(Long id);

    Subcategory updateSubcategory(Subcategory subcategory);

    void deleteSubcategory(Long id);
}
