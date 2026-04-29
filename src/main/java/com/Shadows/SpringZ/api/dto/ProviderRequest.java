package com.Shadows.SpringZ.api.dto;

/**
 * Provider create/update request.
 *
 * Note: Provider extends User. Password is accepted on request but is not returned.
 */
public record ProviderRequest(
        String name,
        Double salary,
        String phone,
        Integer age,
        String email,
        String password,
        String matricule,
        String service,
        String company
) {
}
