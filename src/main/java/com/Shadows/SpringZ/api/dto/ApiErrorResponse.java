package com.Shadows.SpringZ.api.dto;

import java.time.Instant;

/**
 * Standard error payload for the JSON API.
 *
 * Kept intentionally small for Postman usage.
 */
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
