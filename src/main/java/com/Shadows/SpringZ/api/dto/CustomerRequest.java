package com.Shadows.SpringZ.api.dto;

/**
 * Customer create/update request.
 *
 * Note: Customer extends User. Password is accepted on request but is not returned.
 */
public record CustomerRequest(
        String name,
        Double salary,
        String phone,
        Integer age,
        String email,
        String password,
        String address,
        String city
) {
}
