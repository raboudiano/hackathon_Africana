package com.Shadows.SpringZ.api.dto;

/**
 * User response.
 *
 * Password is intentionally excluded.
 */
public record UserResponse(
        Long id,
        String name,
        Double salary,
        String phone,
        Integer age,
        String email
) {
}
