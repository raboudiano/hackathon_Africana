package com.Shadows.SpringZ.api.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Order create/update request.
 */
public record OrderRequest(
        String ref,
        double price,
        LocalDate date,
        Long customerId,
        List<Long> productIds
) {
}
