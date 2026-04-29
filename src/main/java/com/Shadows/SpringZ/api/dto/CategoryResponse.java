package com.Shadows.SpringZ.api.dto;

/**
 * Category response.
 */
public record CategoryResponse(
        Long id,
        String title,
        String description
) {
}
