package com.Shadows.SpringZ.service.impl;

import com.Shadows.SpringZ.model.Subcategory;
import com.Shadows.SpringZ.repository.SubcategoryRepository;
import com.Shadows.SpringZ.service.SubcategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public Subcategory createSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Subcategory getSubcategoryByID(Long id) {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subcategory not found: id=" + id));
    }

    @Override
    public Subcategory updateSubcategory(Subcategory subcategory) {
        return subcategoryRepository.saveAndFlush(subcategory);
    }

    @Override
    public void deleteSubcategory(Long id) {
        subcategoryRepository.deleteById(id);
    }
}
