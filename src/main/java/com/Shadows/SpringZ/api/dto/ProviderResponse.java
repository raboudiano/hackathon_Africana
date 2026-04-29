package com.Shadows.SpringZ.api.dto;

/**
 * Provider response.
 *
 * Password is intentionally excluded.
 */
public record ProviderResponse(
        Long id,
        String name,
        Double salary,
        String phone,
        Integer age,
        String email,
        String matricule,
        String service,
        String company
) {
}
