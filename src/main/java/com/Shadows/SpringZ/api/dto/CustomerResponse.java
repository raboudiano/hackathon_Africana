package com.Shadows.SpringZ.api.dto;

/**
 * Customer response.
 *
 * Password is intentionally excluded.
 */
public record CustomerResponse(
        Long id,
        String name,
        Double salary,
        String phone,
        Integer age,
        String email,
        String address,
        String city
) {
}
