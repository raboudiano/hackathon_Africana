package com.Shadows.SpringZ.api.dto;

/**
 * Subcategory create/update request.
 */
public record SubcategoryRequest(
        String title,
        String description,
        Long categoryId
) {
}
