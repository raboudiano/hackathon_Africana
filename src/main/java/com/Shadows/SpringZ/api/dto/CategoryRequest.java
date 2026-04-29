package com.Shadows.SpringZ.api.dto;

/**
 * Category create/update request.
 */
public record CategoryRequest(
        String title,
        String description
) {
}
