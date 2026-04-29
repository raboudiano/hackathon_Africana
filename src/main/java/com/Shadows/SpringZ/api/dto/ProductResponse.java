package com.Shadows.SpringZ.api.dto;

/**
 * Product response.
 */
public record ProductResponse(
        Long id,
        String name,
        double price,
        String description,
        Long providerId,
        Long subcategoryId
) {
}
