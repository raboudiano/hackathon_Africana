package com.Shadows.SpringZ.api.dto;

/**
 * Subcategory response.
 */
public record SubcategoryResponse(
        Long id,
        String title,
        String description,
        Long categoryId
) {
}
