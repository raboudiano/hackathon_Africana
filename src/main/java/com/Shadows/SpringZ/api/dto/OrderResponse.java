package com.Shadows.SpringZ.api.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Order response.
 */
public record OrderResponse(
        Long id,
        String ref,
        double price,
        LocalDate date,
        Long customerId,
        List<Long> productIds
) {
}
