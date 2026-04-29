package com.Shadows.SpringZ.api.dto;

/**
 * User create/update request.
 *
 * Password is accepted on request but is not returned.
 */
public record UserRequest(
        String name,
        Double salary,
        String phone,
        Integer age,
        String email,
        String password
) {
}
