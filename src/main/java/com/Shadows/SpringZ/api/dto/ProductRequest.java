package com.Shadows.SpringZ.api.dto;

/**
 * Product create/update request.
 */
public record ProductRequest(
        String name,
        double price,
        String description,
        Long providerId,
        Long subcategoryId
) {
}
